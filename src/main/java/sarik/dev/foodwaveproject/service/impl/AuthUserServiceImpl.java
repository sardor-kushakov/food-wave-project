package sarik.dev.foodwaveproject.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;
import sarik.dev.foodwaveproject.exception.ResourceNotFoundException;
import sarik.dev.foodwaveproject.mapper.AuthUserMapper;
import sarik.dev.foodwaveproject.service.AuthUserService;

import java.util.List;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthUserMapper authUserMapper;

    public AuthUserServiceImpl(AuthUserRepository userRepository, PasswordEncoder passwordEncoder, AuthUserMapper authUserMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authUserMapper = authUserMapper;
    }


    @Transactional
    public AuthUserResponseDto registerUser(CreateAuthUserDto userDTO) {
        if (userRepository.existsByEmail(userDTO.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        AuthUser user = new AuthUser();
        user.setEmail(userDTO.email());
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        user.setName(userDTO.name());
        AuthUser saved = userRepository.save(user);
        return authUserMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional
    public AuthUserResponseDto createUser(CreateAuthUserDto createAuthUserDTO) {
        AuthUser user = new AuthUser();
        AuthUser authUser = authUserMapper.partialCreateAuthUser(createAuthUserDTO, user);
        AuthUser saved = userRepository.save(authUser);
        return authUserMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional
    public AuthUserResponseDto getUserById(Long id) {
        AuthUser user = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("user not found"));
       return authUserMapper.toResponseDTO(user);
    }

    @Override
    @Transactional
    public List<AuthUserResponseDto> getAllUsers() {
        List<AuthUser> all = userRepository.findAll();
        return authUserMapper.toResponseDTOList(all);
    }

    @Override
    @Transactional
    public AuthUserResponseDto updateUser(Long id, UpdateAuthUserDto updateAuthUserDTO) {
        AuthUser user = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("user not found"));
        AuthUser authUser = authUserMapper.partialUpdateAuthUser(updateAuthUserDTO, user);
        return authUserMapper.toResponseDTO(authUser);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}

