package sarik.dev.foodwaveproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwaveproject.dto.notification.NotificationCreateDto;
import sarik.dev.foodwaveproject.dto.notification.NotificationDto;
import sarik.dev.foodwaveproject.dto.notification.NotificationResponseDto;
import sarik.dev.foodwaveproject.dto.notification.NotificationUpdateDto;
import sarik.dev.foodwaveproject.entity.Notification;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;
import sarik.dev.foodwaveproject.mapper.NotificationMapper;
import sarik.dev.foodwaveproject.repository.AuthUserRepository;
import sarik.dev.foodwaveproject.repository.NotificationRepository;
import sarik.dev.foodwaveproject.service.NotificationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;
    private final NotificationMapper mapper;
    private final AuthUserRepository userRepository; // AuthUser ma’lumotini olish uchun

    @Override
    public NotificationResponseDto create(NotificationCreateDto dto) {
        // AuthUser obyektini bazadan topish
        AuthUser user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("AuthUser not found with id: " + dto.userId()));

        // Notification obyektini yaratish va userni o‘rnatish
        Notification notification = mapper.fromCreateDto(dto);
        notification.setUser(user); // Userni o‘rnatish

        // Bazaga saqlash
        notification = repository.save(notification);

        // Javob qaytarish
        return mapper.toResponseDto(notification);
    }

    @Override
    public NotificationDto get(Long id) {
        Notification notification = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
        return mapper.toDto(notification);
    }

    @Override
    public List<NotificationDto> getAllByUserId(Long userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDto> getAllByUserIdAndIsRead(Long userId, boolean isRead) {
        return repository.findByUserIdAndIsRead(userId, isRead)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public NotificationDto update(Long id, NotificationUpdateDto dto) {
        Notification notification = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
        notification.setRead(dto.isRead());
        repository.save(notification);
        return mapper.toDto(notification);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Notification not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
