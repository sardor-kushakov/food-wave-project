package sarik.dev.foodwaveproject.dto.cart;
import lombok.Data;

import java.util.List;

@Data
public class CartCreateDto {
    private List<CartItemCreateDto> cartItems; // Savatchadagi har bir mahsulot uchun DTO
}