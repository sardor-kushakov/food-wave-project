package sarik.dev.foodwaveproject.dto.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {

    private Long cartItemId; // CartItem ID

    private Long productId; // Mahsulot IDsi

    private Integer quantity; // Mahsulot miqdori

    private Integer discountPercentage; // Chegirma foizi

    private Long productPrice; // Mahsulotning bir dona narxi

    private Long totalPrice; // Jami narx (hisoblab beriladi)
}
