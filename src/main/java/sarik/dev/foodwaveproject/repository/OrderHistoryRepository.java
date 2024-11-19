package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sarik.dev.foodwaveproject.entity.Order;
import sarik.dev.foodwaveproject.entity.OrderHistory;
import sarik.dev.foodwaveproject.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
    // Muayyan bir buyurtmaga tegishli barcha tarixiy yozuvlarni olish
    List<OrderHistory> findByOrder(Order order);

    // Buyurtma holati bo'yicha tarixiy yozuvlarni olish
    List<OrderHistory> findByStatus(OrderStatus status);

    // Muayyan buyurtma va holat bo'yicha tarixiy yozuvlarni olish
    List<OrderHistory> findByOrderAndStatus(Order order, OrderStatus status);

    // Ma'lum bir vaqt oralig'ida o'zgargan tarixiy yozuvlarni olish
    List<OrderHistory> findByChangeDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Buyurtma holati o'zgartirilgan vaqtda aniqlash uchun eng oxirgi tarixiy yozuv
    OrderHistory findTopByOrderOrderByChangeDateDesc(Order order);
}
