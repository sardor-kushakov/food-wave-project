package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwaveproject.entity.CartItem;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Savat ID bo'yicha barcha elementlarni olish
    List<CartItem> findByCartId(Long cartId);

    // Savat ID va Mahsulot ID bo'yicha elementni topish
    CartItem findByCartIdAndProductId(Long cartId, Long productId);

    // Savat ID bo'yicha barcha elementlarni o'chirish
    void deleteByCartId(Long cartId);

    // Savat ID va Mahsulot ID bo'yicha elementni o'chirish
    void deleteByCartIdAndProductId(Long cartId, Long productId);
}
