package sarik.dev.foodwaveproject.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private Long productId; // Mahsulot ID

    private String name; // Mahsulot nomi

    private String description; // Mahsulot tavsifi

    private Long price; // Mahsulot narxi

    private Boolean available; // Mahsulot mavjudligi

    private Long categoryId; // Mahsulot kategoriyasi ID
}
