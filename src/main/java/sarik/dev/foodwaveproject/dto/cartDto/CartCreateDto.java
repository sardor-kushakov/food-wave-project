package sarik.dev.foodwaveproject.dto.cartDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import sarik.dev.foodwaveproject.dto.cartItemDto.CartItemCreateDto;

import java.util.List;

@Data
public class CartCreateDto {
    @NotNull
    private Long userId;
    private List<CartItemCreateDto> cartItems; // Savatchadagi har bir mahsulot uchun DTO
}