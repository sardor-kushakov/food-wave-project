package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sarik.dev.foodwaveproject.entity.Product;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<Product, Long> {
    @Query("SELECT oi.product.id, SUM(oi.quantity) AS totalQuantity " +
            "FROM OrderItem oi " +
            "GROUP BY oi.product.id " +
            "ORDER BY totalQuantity DESC")
    List<Object[]> findProductsByPopularity();
}
