package sarik.dev.foodwaveproject.dto.cartDto;

import lombok.Data;
import sarik.dev.foodwaveproject.dto.cartItemDto.CartItemUpdateDto;

import java.util.List;

@Data
public class CartUpdateDto {
    private Long cartId;
    private List<CartItemUpdateDto> cartItems; // Har bir mahsulot uchun yangilanish ma'lumotlari
}
