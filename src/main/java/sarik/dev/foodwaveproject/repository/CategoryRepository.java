package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sarik.dev.foodwaveproject.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
