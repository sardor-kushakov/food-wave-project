package sarik.dev.foodwaveproject.dto.cart.item;

import java.io.Serializable;

public record CartItemResponseDto(
        Long id,
        Long cartId,
        Long productId,
        String productName,
        Integer quantity,
        Integer discountPercentage,
        Long productPrice,
        Long totalPrice) implements Serializable {
}
