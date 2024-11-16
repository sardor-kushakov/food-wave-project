package sarik.dev.foodwaveproject.service.authUser;

import sarik.dev.foodwaveproject.dto.auth.AuthUserResponseDto;
import sarik.dev.foodwaveproject.dto.auth.CreateAuthUserDto;
import sarik.dev.foodwaveproject.dto.auth.UpdateAuthUserDto;

import java.util.List;

public interface AuthUserService {
    AuthUserResponseDto createUser(CreateAuthUserDto createAuthUserDTO);

    AuthUserResponseDto getUserById(Long id);

    List<AuthUserResponseDto> getAllUsers();

    AuthUserResponseDto updateUser(Long id, UpdateAuthUserDto updateAuthUserDTO);

    void deleteUserById(Long id);
}
