package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwaveproject.entity.auth.AuthRole;

import java.util.Optional;

@Repository
public interface AuthRoleRepository extends JpaRepository<AuthRole, Integer> {

    // Nom orqali rolni topish
    Optional<AuthRole> findByName(String name);
}
