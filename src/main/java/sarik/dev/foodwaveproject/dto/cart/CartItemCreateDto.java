package sarik.dev.foodwaveproject.dto.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemCreateDto {

    @NotNull(message = "Product ID is required") // Mahsulot IDsi majburiy
    private Long productId;

    @NotNull(message = "Quantity is required") // Miqdor majburiy
    @Min(value = 1, message = "Quantity must be at least 1") // Kamida 1 bo'lishi kerak
    private Integer quantity;

    @Min(value = 0, message = "Discount percentage must be at least 0") // Chegirma foizi 0 dan kam bo'lmasligi kerak
    private Integer discountPercentage = 0; // Chegirma, ixtiyoriy, default 0
}
