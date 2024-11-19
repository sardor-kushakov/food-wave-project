package sarik.dev.foodwaveproject.dto.cart;
import lombok.Data;
import sarik.dev.foodwaveproject.dto.cart.item.CartItemCreateDto;

import java.util.List;

@Data
public class CartCreateDto {
    private List<CartItemCreateDto> cartItems; // Savatchadagi har bir mahsulot uchun DTO
}