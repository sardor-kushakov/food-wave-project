package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwaveproject.entity.OrderItem;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // Buyurtma ID bo'yicha buyurtma elementlarini olish
    List<OrderItem> findByOrderId(Long orderId);

    // Mahsulot ID bo'yicha buyurtma elementlarini olish
    List<OrderItem> findByProductId(Long productId);

    // Buyurtma ID va Mahsulot ID bo'yicha buyurtma elementini olish
    OrderItem findByOrderIdAndProductId(Long orderId, Long productId);
}
