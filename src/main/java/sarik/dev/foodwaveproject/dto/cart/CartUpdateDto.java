package sarik.dev.foodwaveproject.dto.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartUpdateDto {

    @NotNull(message = "Cart ID is required") // Savat IDsi bo'lishi kerak
    private Long cartId;

    @NotNull(message = "User ID is required") // Foydalanuvchi IDsi bo'lishi kerak
    private Long userId;

    @NotNull(message = "Cart items cannot be null") // Savatdagi mahsulotlar bo'sh bo'lmasligi kerak
    private List<CartItemUpdateDto> cartItems; // Savatdagi mahsulotlar ro'yxati
}
