package sarik.dev.foodwaveproject.dto.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartCreateDto {

    @NotNull(message = "User ID is required") // Foydalanuvchi IDsi bo'lishi kerak
    private Long userId;

    @NotNull(message = "Cart items cannot be null") // Savatdagi mahsulotlar bo'sh bo'lmasligi kerak
    private List<CartItemCreateDto> cartItems; // Savatdagi mahsulotlar ro'yxati
}
