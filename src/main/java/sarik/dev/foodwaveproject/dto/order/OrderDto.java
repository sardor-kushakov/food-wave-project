package sarik.dev.foodwaveproject.dto.order;

import lombok.Getter;
import lombok.Setter;
import sarik.dev.foodwaveproject.enums.OrderStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDto {

    private Long orderId; // Buyurtma ID

    private Long userId; // Buyurtmani bergan foydalanuvchi ID

    private LocalDateTime orderDate; // Buyurtma berilgan vaqt

    private Long totalAmount; // Buyurtmaning umumiy summasi

    private OrderStatus status; // Buyurtma holati (PENDING, COMPLETED, CANCELED)

}
