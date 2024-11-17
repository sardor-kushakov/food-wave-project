package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwaveproject.entity.OrderHistory;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

    // Buyurtma ID bo'yicha buyurtma tarixini olish
    List<OrderHistory> findByOrderId(Long orderId);
}
