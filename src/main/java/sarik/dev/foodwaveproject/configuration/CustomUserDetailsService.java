package sarik.dev.foodwaveproject.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sarik.dev.foodwaveproject.entity.auth.AuthRole;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;
import sarik.dev.foodwaveproject.repository.AuthRoleRepository;
import sarik.dev.foodwaveproject.repository.AuthUserRepository;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;
    private final AuthRoleRepository authRoleRepository;

    public CustomUserDetailsService(AuthUserRepository authUserRepository, AuthRoleRepository authRoleRepository) {
        this.authUserRepository = authUserRepository;
        this.authRoleRepository = authRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AuthUser authUser = authUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("bad credentials"));
        Set<AuthRole> authRoles = authRoleRepository.findAllById(authUser.getId());

        authUser.setRoles(authRoles);

        return new CustomUserDetails(authUser);

    }
}
