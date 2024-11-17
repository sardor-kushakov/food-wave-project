package sarik.dev.foodwaveproject.service;

import sarik.dev.foodwaveproject.dto.cart.CartCreateDto;
import sarik.dev.foodwaveproject.dto.cart.CartDto;
import sarik.dev.foodwaveproject.dto.cart.CartResponseDto;
import sarik.dev.foodwaveproject.dto.cart.CartUpdateDto;

import java.util.List;

public interface CartService {

    // Yangi savat yaratish
    CartResponseDto create(CartCreateDto createDto);

    // ID bo'yicha savatni olish
    CartDto getById(Long id);

    // Foydalanuvchi ID bo'yicha savatni olish
    CartResponseDto getByUserId(Long userId);

    // Barcha savatlarni olish
    List<CartResponseDto> getAll();

    // Savatni yangilash
    CartResponseDto update(Long id, CartUpdateDto updateDto);

    // Savatni o'chirish
    void delete(Long id);
}
