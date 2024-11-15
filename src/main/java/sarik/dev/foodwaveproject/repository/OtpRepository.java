package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sarik.dev.foodwaveproject.entity.Otp;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {

  Optional<Otp> findByEmail(String email);

  void deleteByEmail(String email);
}