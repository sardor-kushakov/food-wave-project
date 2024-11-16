package sarik.dev.foodwaveproject.service;

import sarik.dev.foodwaveproject.dto.cart.CartCreateDto;
import sarik.dev.foodwaveproject.dto.cart.CartResponseDto;
import sarik.dev.foodwaveproject.dto.cart.CartUpdateDto;

public interface CartService {
    CartResponseDto createCart(CartCreateDto cartCreateDto);

    CartResponseDto getCartByUserId(Long userId);

    CartResponseDto updateCart(CartUpdateDto cartUpdateDto); // Savatchani yangilash uchun yangi metod

    void deleteCartById(Long cartId);
}