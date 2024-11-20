package sarik.dev.foodwaveproject.service;

import sarik.dev.foodwaveproject.dto.notification.NotificationCreateDto;
import sarik.dev.foodwaveproject.dto.notification.NotificationDto;
import sarik.dev.foodwaveproject.dto.notification.NotificationResponseDto;
import sarik.dev.foodwaveproject.dto.notification.NotificationUpdateDto;

import java.util.List;

public interface NotificationService {

    // Yangi xabar yaratadi va qaytaradi
    NotificationResponseDto create(NotificationCreateDto dto);

    // ID bo'yicha bitta xabarni qaytaradi
    NotificationDto get(Long id);

    // Foydalanuvchi ID bo'yicha barcha xabarlarni qaytaradi
    List<NotificationDto> getAllByUserId(Long userId);

    // Foydalanuvchi ID va o'qilgan holat bo'yicha xabarlarni qaytaradi
    List<NotificationDto> getAllByUserIdAndIsRead(Long userId, boolean isRead);

    // ID bo'yicha xabarni yangilaydi va qaytaradi
    NotificationDto update(Long id, NotificationUpdateDto dto);

    // ID bo'yicha xabarni o'chiradi
    void delete(Long id);
}
