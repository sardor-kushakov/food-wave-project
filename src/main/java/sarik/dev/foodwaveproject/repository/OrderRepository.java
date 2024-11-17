package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwaveproject.entity.Order;
import sarik.dev.foodwaveproject.enums.OrderStatus;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Foydalanuvchi ID bo'yicha buyurtmalarni olish
    List<Order> findByCustomerId(Long customerId);

    // Buyurtma holati bo'yicha qidirish
    List<Order> findByStatus(OrderStatus status);
}
