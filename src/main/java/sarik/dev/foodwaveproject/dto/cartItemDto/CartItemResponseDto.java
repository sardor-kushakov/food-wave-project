package sarik.dev.foodwaveproject.dto.cartItemDto;



import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import sarik.dev.foodwaveproject.entity.CartItem;

@Data
public class CartItemResponseDto {
    private Long cartItemId;
    private Long productId;
    @Positive(message = "Quantity must be greater than zero")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 100, message = "Quantity cannot exceed 100")
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