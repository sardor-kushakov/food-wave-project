package sarik.dev.foodwaveproject.service;

import sarik.dev.foodwaveproject.dto.cart.item.CartItemCreateDto;
import sarik.dev.foodwaveproject.dto.cart.item.CartItemDto;
import sarik.dev.foodwaveproject.dto.cart.item.CartItemResponseDto;
import sarik.dev.foodwaveproject.dto.cart.item.CartItemUpdateDto;

import java.util.List;

public interface CartItemService {

    // Yangi savat elementi qo'shish
    CartItemResponseDto create(CartItemCreateDto createDto);

    // ID bo'yicha savat elementini olish
    CartItemDto getById(Long id);

    // Savat ID bo'yicha barcha elementlarni olish
    List<CartItemResponseDto> getByCartId(Long cartId);

    // Savat elementini yangilash
    CartItemResponseDto update(Long id, CartItemUpdateDto updateDto);

    // Savat elementini o'chirish
    void delete(Long id);

    // Savat ID bo'yicha barcha elementlarni o'chirish
    void deleteByCartId(Long cartId);

    // Savat ID va Mahsulot ID bo'yicha elementni o'chirish
    void deleteByCartIdAndProductId(Long cartId, Long productId);
}
