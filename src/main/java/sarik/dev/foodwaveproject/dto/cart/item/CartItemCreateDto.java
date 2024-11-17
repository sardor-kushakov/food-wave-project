package sarik.dev.foodwaveproject.dto.cart.item;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public record CartItemCreateDto(
        @NotNull(message = "Cart ID cannot be null")
        Long cartId,

        @NotNull(message = "Product ID cannot be null")
        Long productId,

        @Positive(message = "Quantity must be greater than 0")
        Integer quantity,

        @Positive(message = "Product price must be positive")
        Long productPrice,

        Integer discountPercentage) implements Serializable {
}
