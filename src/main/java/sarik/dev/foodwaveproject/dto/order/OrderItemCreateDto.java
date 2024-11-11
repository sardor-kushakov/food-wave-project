package sarik.dev.foodwaveproject.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemCreateDto {

    @NotNull(message = "Product ID is required") // Mahsulot ID majburiy
    private Long productId; // Mahsulot ID

    @NotNull(message = "Quantity is required") // Mahsulot miqdori majburiy
    @Min(value = 1, message = "Quantity must be greater than 0") // Miqdor 0 dan katta bo'lishi kerak
    private Double quantity; // Mahsulot miqdori

    @NotNull(message = "Unit price is required") // Mahsulotning bir dona narxi majburiy
    @Min(value = 0, message = "Unit price must be a positive number") // Narx manfiy bo'lmasligi kerak
    private Long unitPrice; // Mahsulotning bir dona narxi
}
