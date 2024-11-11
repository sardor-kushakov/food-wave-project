package sarik.dev.foodwaveproject.dto.cart;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.dto.CartItemDto;

import java.util.List;

@Getter
@Setter
public class CartDto {

    private Long cartId; // Savatning ID raqami

    private Long userId; // Savat tegishli foydalanuvchi IDsi

    private List<CartItemDto> cartItems; // Savatdagi mahsulotlar ro'yxati

    private Long totalPrice; // Savatning umumiy narxi
}
