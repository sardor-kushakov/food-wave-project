package sarik.dev.foodwaveproject.dto.cartItemDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartItemUpdateDto {
    private Long cartItemId;
    @Positive
    @Max(value = 100, message = "Quantity cannot exceed 100")
    private Integer quantity;

    private long discountSom;
}