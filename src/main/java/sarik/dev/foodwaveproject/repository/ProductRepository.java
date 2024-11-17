package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwaveproject.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Nom bo'yicha mahsulotlarni qidirish
    List<Product> findByNameContainingIgnoreCase(String name);

    // Kategoriya ID bo'yicha mahsulotlarni qidirish
    List<Product> findByCategoryId(Long categoryId);

    // Faol bo'lgan mahsulotlarni olish
    List<Product> findByAvailableTrue();
}
