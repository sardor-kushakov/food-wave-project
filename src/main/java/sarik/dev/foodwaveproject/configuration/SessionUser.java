package sarik.dev.foodwaveproject.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;
import sarik.dev.foodwaveproject.exception.UserNotAuthenticatedException;

@Component
public class SessionUser {

    public AuthUser getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotAuthenticatedException("Foydalanuvchi autentifikatsiyadan o'tmagan!");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails currentUser) {
            return currentUser.getAuthUser();
        } else
            throw new UserNotAuthenticatedException("Foydalanuvchi ma'lumoti noto'g'ri!");
    }



}
