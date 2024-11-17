package sarik.dev.foodwaveproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwaveproject.dto.auth.user.AuthUserDto;
import sarik.dev.foodwaveproject.dto.auth.user.AuthUserResponseDto;
import sarik.dev.foodwaveproject.dto.auth.user.AuthUserUpdateDto;
import sarik.dev.foodwaveproject.dto.request.auth.LoginRequest;
import sarik.dev.foodwaveproject.dto.request.auth.ProfileUpdateRequest;
import sarik.dev.foodwaveproject.dto.request.auth.RegisterRequest;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;
import sarik.dev.foodwaveproject.exception.AuthenticationException;
import sarik.dev.foodwaveproject.exception.ResourceNotFoundException;
import sarik.dev.foodwaveproject.mapper.AuthUserMapper;
import sarik.dev.foodwaveproject.repository.AuthUserRepository;
import sarik.dev.foodwaveproject.service.AuthUserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserRepository authUserRepository;
    private final AuthUserMapper authUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthUserResponseDto register(RegisterRequest registerRequest) {
        // Yangi foydalanuvchini yaratish
        AuthUser authUser = new AuthUser();
        authUser.setName(registerRequest.name());
        authUser.setEmail(registerRequest.email());
        authUser.setPassword(passwordEncoder.encode(registerRequest.password()));
        authUser.setVerified(registerRequest.isVerified());

        AuthUser savedUser = authUserRepository.save(authUser);
        return authUserMapper.toResponseDto(savedUser);
    }

    @Override
    public AuthUserDto login(LoginRequest loginRequest) {
        // Email orqali foydalanuvchini topish
        AuthUser authUser = authUserRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new AuthenticationException("Invalid email or password"));

        // Parolni tekshirish
        if (!passwordEncoder.matches(loginRequest.password(), authUser.getPassword())) {
            throw new AuthenticationException("Invalid email or password");
        }

        return authUserMapper.toDto(authUser);
    }

    @Override
    public AuthUserDto getById(Long id) {
        AuthUser authUser = authUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", id));
        return authUserMapper.toDto(authUser);
    }

    @Override
    public List<AuthUserResponseDto> getAll() {
        List<AuthUser> users = authUserRepository.findAll();
        return users.stream()
                .map(authUserMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthUserResponseDto updateProfile(Long id, ProfileUpdateRequest profileUpdateRequest) {
        AuthUser authUser = authUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", id));

        authUserMapper.updateFromProfileRequest(profileUpdateRequest, authUser);

        AuthUser updatedUser = authUserRepository.save(authUser);
        return authUserMapper.toResponseDto(updatedUser);
    }

    @Override
    public AuthUserResponseDto updateUser(Long id, AuthUserUpdateDto updateDto) {
        AuthUser authUser = authUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", id));

        authUserMapper.fromUpdateDto(updateDto, authUser);

        AuthUser updatedUser = authUserRepository.save(authUser);
        return authUserMapper.toResponseDto(updatedUser);
    }

    @Override
    public void delete(Long id) {
        AuthUser authUser = authUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", id));
        authUserRepository.delete(authUser);
    }
}
