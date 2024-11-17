package sarik.dev.foodwaveproject.service;

import sarik.dev.foodwaveproject.dto.auth.user.AuthUserDto;
import sarik.dev.foodwaveproject.dto.auth.user.AuthUserResponseDto;
import sarik.dev.foodwaveproject.dto.auth.user.AuthUserUpdateDto;
import sarik.dev.foodwaveproject.dto.request.auth.LoginRequest;
import sarik.dev.foodwaveproject.dto.request.auth.ProfileUpdateRequest;
import sarik.dev.foodwaveproject.dto.request.auth.RegisterRequest;

import java.util.List;

public interface AuthUserService {

    // Yangi foydalanuvchi ro'yxatdan o'tkazish
    AuthUserResponseDto register(RegisterRequest registerRequest);

    // Login qilish
    AuthUserDto login(LoginRequest loginRequest);

    // Foydalanuvchini ID orqali olish
    AuthUserDto getById(Long id);

    // Barcha foydalanuvchilarni olish
    List<AuthUserResponseDto> getAll();

    // Profilni yangilash
    AuthUserResponseDto updateProfile(Long id, ProfileUpdateRequest profileUpdateRequest);

    // AuthUserUpdateDto orqali foydalanuvchini yangilash
    AuthUserResponseDto updateUser(Long id, AuthUserUpdateDto updateDto);

    // Foydalanuvchini o'chirish
    void delete(Long id);
}
