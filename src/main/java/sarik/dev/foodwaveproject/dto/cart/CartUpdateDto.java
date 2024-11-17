package sarik.dev.foodwaveproject.dto.cart;

import jakarta.validation.constraints.NotNull;
import sarik.dev.foodwaveproject.dto.cart.item.CartItemUpdateDto;

import java.io.Serializable;
import java.util.List;

public record CartUpdateDto(
        @NotNull(message = "Cart ID cannot be null")
        Long cartId,

        List<CartItemUpdateDto> cartItems) implements Serializable {
}
