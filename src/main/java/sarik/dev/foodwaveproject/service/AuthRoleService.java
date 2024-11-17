package sarik.dev.foodwaveproject.service;

import sarik.dev.foodwaveproject.dto.auth.role.AuthRoleCreateDto;
import sarik.dev.foodwaveproject.dto.auth.role.AuthRoleDto;
import sarik.dev.foodwaveproject.dto.auth.role.AuthRoleResponseDto;
import sarik.dev.foodwaveproject.dto.auth.role.AuthRoleUpdateDto;

import java.util.List;

public interface AuthRoleService {

    // Yangi rol yaratish
    AuthRoleResponseDto create(AuthRoleCreateDto createDto);

    // ID bo'yicha rolni olish
    AuthRoleDto getById(Integer id);

    // Nom bo'yicha rolni olish
    AuthRoleDto getByName(String name);

    // Barcha rollarni olish
    List<AuthRoleResponseDto> getAll();

    // Rolni yangilash
    AuthRoleResponseDto update(Integer id, AuthRoleUpdateDto updateDto);

    // Rolni o'chirish
    void delete(Integer id);
}
