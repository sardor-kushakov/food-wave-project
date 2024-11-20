package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwaveproject.entity.Category;
import sarik.dev.foodwaveproject.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {
    List<Product> findByCategory(Category category);

    Optional<Product> findByProductName(String name);

//    @Override
//    @Query(value = "select p. , p.description , p.price , p.ingredients , p.image , p.discount  from Product p")
//    List<Product> findAll2();
}
