package sarik.dev.foodwaveproject.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import sarik.dev.foodwaveproject.dto.cart.item.CartItemResponseDto;

import java.util.List;

@Data
@AllArgsConstructor
public class CartResponseDto {
    private Long cartId;
    private Long userId;
    private List<CartItemResponseDto> cartItems;
    private long totalPriceSom; // Umumiy narx foydalanuvchiga so'mda qaytariladi
}
