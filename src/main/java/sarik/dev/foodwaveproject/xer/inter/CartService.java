package sarik.dev.foodwaveproject.xer.inter;

public interface CartService {
    CartResponseDto createCart(CartCreateDto cartCreateDto);

    CartResponseDto getCartByUserId(Long userId);

    CartResponseDto updateCart(CartUpdateDto cartUpdateDto); // Savatchani yangilash uchun yangi metod

    void deleteCartById(Long cartId);
}