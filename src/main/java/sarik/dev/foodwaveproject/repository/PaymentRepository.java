package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwaveproject.entity.Payment;
import sarik.dev.foodwaveproject.enums.PaymentMethod;
import sarik.dev.foodwaveproject.enums.PaymentStatus;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Buyurtma ID bo'yicha to'lovni topish
    Payment findByOrderId(Long orderId);

    // To'lov holati bo'yicha qidirish
    List<Payment> findByStatus(PaymentStatus status);

    // To'lov usuli bo'yicha qidirish
    List<Payment> findByPaymentMethod(PaymentMethod paymentMethod);
}
