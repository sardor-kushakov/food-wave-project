package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwaveproject.dto.categoryDto.CategoryResponseDTO;
import sarik.dev.foodwaveproject.entity.Category;
import sarik.dev.foodwaveproject.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product , Integer> {
    List<Product> findByCategory(Category category);
}
