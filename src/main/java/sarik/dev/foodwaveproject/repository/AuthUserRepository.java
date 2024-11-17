package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    // Foydalanuvchini email orqali topish
    Optional<AuthUser> findByEmail(String email);

    // Email mavjudligini tekshirish
    boolean existsByEmail(String email);
}
