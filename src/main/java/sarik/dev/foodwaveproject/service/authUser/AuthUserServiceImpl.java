package sarik.dev.foodwaveproject.service.authUser;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sarik.dev.foodwaveproject.dto.authUserDto.CreateAuthUserDTO;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;
import sarik.dev.foodwaveproject.repository.AuthUserRepository;

@Service
public class AuthUserServiceImpl {

    private final AuthUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthUserServiceImpl(AuthUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void registerUser(CreateAuthUserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        AuthUser user = new AuthUser();
        user.setEmail(userDTO.email());
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        user.setName(user.getName());
        userRepository.save(user);
    }
}

