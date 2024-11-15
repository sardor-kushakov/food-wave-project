package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sarik.dev.foodwaveproject.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
