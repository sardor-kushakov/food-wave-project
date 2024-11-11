package sarik.dev.foodwaveproject.dto.order;

import lombok.Getter;
import lombok.Setter;
import sarik.dev.foodwaveproject.enums.OrderStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderHistoryDto {

    private Long orderHistoryId; // OrderHistory ID

    private Long orderId; // Buyurtma ID

    private OrderStatus status; // Buyurtma holati (PENDING, COMPLETED, CANCELED)

    private LocalDateTime changeDate; // Holat o'zgargan vaqt

    private String comments; // Qo'shimcha izoh yoki sabab
}
