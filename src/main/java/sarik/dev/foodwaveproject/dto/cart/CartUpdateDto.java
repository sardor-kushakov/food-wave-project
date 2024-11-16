package sarik.dev.foodwaveproject.dto.cart;

import lombok.Data;

import java.util.List;

@Data
public class CartUpdateDto {
    private Long cartId;
    private List<CartItemUpdateDto> cartItems; // Har bir mahsulot uchun yangilanish ma'lumotlari
}
