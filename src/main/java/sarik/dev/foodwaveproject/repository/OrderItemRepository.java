package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sarik.dev.foodwaveproject.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
