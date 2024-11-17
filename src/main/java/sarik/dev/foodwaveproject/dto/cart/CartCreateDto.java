package sarik.dev.foodwaveproject.dto.cart;

import jakarta.validation.constraints.NotNull;
import sarik.dev.foodwaveproject.dto.cart.item.CartItemCreateDto;

import java.io.Serializable;
import java.util.List;

public record CartCreateDto(
        @NotNull(message = "User ID cannot be null")
        Long userId,

        List<CartItemCreateDto> cartItems) implements Serializable {
}
