package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sarik.dev.foodwaveproject.entity.OrderHistory;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

}
