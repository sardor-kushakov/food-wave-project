package sarik.dev.foodwaveproject.service;

import sarik.dev.foodwaveproject.dto.auth.user.AuthUserResponseDto;
import sarik.dev.foodwaveproject.dto.auth.user.CreateAuthUserDto;
import sarik.dev.foodwaveproject.dto.auth.user.UpdateAuthUserDto;

import java.util.List;

public interface AuthUserService {
    AuthUserResponseDto createUser(CreateAuthUserDto createAuthUserDTO);

    AuthUserResponseDto getUserById(Long id);

    List<AuthUserResponseDto> getAllUsers();

    AuthUserResponseDto updateUser(Long id, UpdateAuthUserDto updateAuthUserDTO);

    void deleteUserById(Long id);
}
