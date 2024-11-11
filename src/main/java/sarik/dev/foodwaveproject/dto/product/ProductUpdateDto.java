package sarik.dev.foodwaveproject.dto.product;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateDto {

    private Long productId; // Mahsulot ID

    private String name; // Mahsulot nomi

    private String description; // Mahsulot tavsifi

    @Positive(message = "Price must be positive") // Narx musbat bo'lishi kerak
    private Long price; // Mahsulot narxi

    private Boolean available; // Mahsulot mavjudligi
}
