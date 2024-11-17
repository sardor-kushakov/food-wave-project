package sarik.dev.foodwaveproject.dto.cart;

import sarik.dev.foodwaveproject.dto.cart.item.CartItemResponseDto;

import java.io.Serializable;
import java.util.List;

public record CartResponseDto(
        Long id,
        Long userId,
        List<CartItemResponseDto> cartItems, // Savatdagi mahsulotlar
        Long totalPrice) implements Serializable {
}
