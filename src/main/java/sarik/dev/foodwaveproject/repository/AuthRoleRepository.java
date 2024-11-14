package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwaveproject.entity.auth.AuthRole;

import java.util.Set;

@Repository
public interface AuthRoleRepository extends JpaRepository<AuthRole,Long> {

    Set<AuthRole> findAllById(Long userId);
}
