package sarik.dev.foodwaveproject.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateDto {

    @NotBlank(message = "Product name is required") // Mahsulot nomi majburiy
    private String name; // Mahsulot nomi

    private String description; // Mahsulot tavsifi

    @NotNull(message = "Price is required") // Narx majburiy
    @Positive(message = "Price must be positive") // Narx musbat bo'lishi kerak
    private Long price; // Mahsulot narxi

    @NotNull(message = "Category ID is required") // Kategoriyasi ID majburiy
    private Long categoryId; // Mahsulot kategoriyasi ID
}
