package sarik.dev.foodwaveproject.service.authUser;

import sarik.dev.foodwaveproject.dto.authUserDto.AuthUserResponseDTO;
import sarik.dev.foodwaveproject.dto.authUserDto.CreateAuthUserDTO;
import sarik.dev.foodwaveproject.dto.authUserDto.UpdateAuthUserDTO;

import java.util.List;

public interface AuthUserService {
    AuthUserResponseDTO createUser(CreateAuthUserDTO createAuthUserDTO);

    AuthUserResponseDTO getUserById(Long id);

    List<AuthUserResponseDTO> getAllUsers();

    AuthUserResponseDTO updateUser(Long id, UpdateAuthUserDTO updateAuthUserDTO);

    void deleteUserById(Long id);
}
