package sarik.dev.foodwaveproject.xer.inter;

import java.util.List;

public interface AuthUserService {
    AuthUserResponseDto createUser(CreateAuthUserDto createAuthUserDTO);

    AuthUserResponseDto getUserById(Long id);

    List<AuthUserResponseDto> getAllUsers();

    AuthUserResponseDto updateUser(Long id, UpdateAuthUserDto updateAuthUserDTO);

    void deleteUserById(Long id);
}
