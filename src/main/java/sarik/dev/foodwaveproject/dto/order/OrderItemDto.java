package sarik.dev.foodwaveproject.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {

    private Long orderItemId; // Order item ID

    private Double quantity; // Mahsulot miqdori

    private Long unitPrice; // Mahsulotning bir dona narxi

    private Long totalPrice; // Mahsulotning jami narxi

    private Long orderId; // Buyurtma ID

    private Long productId; // Mahsulot ID
}
