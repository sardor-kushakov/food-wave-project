package sarik.dev.foodwaveproject.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductValidDto {

    private Long productId; // Mahsulot ID

    private Long price; // Mahsulot narxi

    private Boolean available; // Mahsulot mavjudligi

    public boolean isValid() {
        return price > 0 && available != null;
    }
}
