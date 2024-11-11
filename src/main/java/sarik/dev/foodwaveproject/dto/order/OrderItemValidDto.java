package sarik.dev.foodwaveproject.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemValidDto {

    @NotNull(message = "Quantity is required") // Mahsulot miqdori majburiy
    @Min(value = 1, message = "Quantity must be greater than 0") // Miqdor 0 dan katta bo'lishi kerak
    private Double quantity; // Mahsulot miqdori

    @NotNull(message = "Unit price is required") // Mahsulotning bir dona narxi majburiy
    @Min(value = 0, message = "Unit price must be a positive number") // Narx manfiy bo'lmasligi kerak
    private Long unitPrice; // Mahsulotning bir dona narxi

    public boolean isValid() {
        return quantity > 0 && unitPrice >= 0;
    }
}
