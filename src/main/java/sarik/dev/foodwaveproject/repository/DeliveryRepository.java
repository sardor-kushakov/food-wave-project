package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwaveproject.entity.Delivery;
import sarik.dev.foodwaveproject.enums.DeliveryStatus;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    // Yetkazib berish holati bo'yicha qidirish
    List<Delivery> findByStatus(DeliveryStatus status);

    // Rejalashtirilgan vaqt oralig'ida qidirish
    List<Delivery> findByScheduledDeliveryTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    // Amalga oshirilgan vaqt bo'yicha qidirish
    List<Delivery> findByActualDeliveryTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    // Yetkazib beruvchi bo'yicha qidirish
    List<Delivery> findByCourierId(Long courierId);

    // Buyurtma bo'yicha yetkazib berish ma'lumotini olish
    Delivery findByOrderId(Long orderId);
}
