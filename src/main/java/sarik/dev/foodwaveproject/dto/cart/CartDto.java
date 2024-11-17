package sarik.dev.foodwaveproject.dto.cart;

import sarik.dev.foodwaveproject.dto.auth.user.AuthUserDto;
import sarik.dev.foodwaveproject.dto.cart.item.CartItemDto;

import java.io.Serializable;
import java.util.List;

public record CartDto(
        Long id,
        AuthUserDto user,
        List<CartItemDto> cartItems,
        Long totalPrice) implements Serializable {
}
