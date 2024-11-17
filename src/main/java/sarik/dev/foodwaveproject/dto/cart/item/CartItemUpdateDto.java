package sarik.dev.foodwaveproject.dto.cart.item;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public record CartItemUpdateDto(
        @NotNull(message = "Cart Item ID cannot be null")
        Long cartItemId,

        @Positive(message = "Quantity must be greater than 0")
        Integer quantity,

        @Positive(message = "Product price must be positive")
        Long productPrice,

        Integer discountPercentage) implements Serializable {
}
