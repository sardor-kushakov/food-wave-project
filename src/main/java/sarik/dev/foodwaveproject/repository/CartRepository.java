package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sarik.dev.foodwaveproject.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
