package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwaveproject.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Nom bo'yicha kategoriyani qidirish
    Optional<Category> findByName(String name);

    // Faol kategoriyalarni olish
    List<Category> findByIsActiveTrue();

    // Faol bo'lmagan kategoriyalarni olish
    List<Category> findByIsActiveFalse();
}
