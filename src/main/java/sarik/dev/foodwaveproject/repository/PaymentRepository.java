package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sarik.dev.foodwaveproject.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
