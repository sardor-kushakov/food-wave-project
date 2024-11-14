package sarik.dev.foodwaveproject.dto.cartItemDto;


import lombok.Data;
import sarik.dev.foodwaveproject.entity.CartItem;

@Data
public class CartItemResponseDto {
    private Long cartItemId;
    private Long productId;
    private Integer quantity;
    private long discountSom; // Foydalanuvchiga so'mda ko'rsatiladi
    private long productPriceSom; // Foydalanuvchiga so'mda ko'rsatiladi

    public CartItemResponseDto(CartItem cartItem) {
        this.cartItemId = cartItem.getCartItemId();
        this.productId = cartItem.getProduct().getId();
        this.quantity = cartItem.getQuantity();
        this.discountSom = cartItem.getDiscount() / 100;
        this.productPriceSom = cartItem.getProductPrice() / 100;
    }
}