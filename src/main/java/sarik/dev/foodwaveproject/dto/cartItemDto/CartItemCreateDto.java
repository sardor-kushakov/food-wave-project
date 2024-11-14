package sarik.dev.foodwaveproject.dto.cartItemDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartItemCreateDto {
    @NotNull
    private Long productId;

    @Positive
    private Integer quantity;

    @Positive
    private long discountSom; // Chegirma foydalanuvchi tomonidan so'mda kiritiladi
}
