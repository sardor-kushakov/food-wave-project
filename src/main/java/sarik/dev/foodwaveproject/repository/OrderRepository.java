package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sarik.dev.foodwaveproject.entity.Order;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserAndDeletedIsFalse(AuthUser user);

    List<Order> findAllByDeletedIsFalse();

    Optional<Order> findByOrderIdAndDeletedFalse(Long orderId);
}
