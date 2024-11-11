package sarik.dev.foodwaveproject.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import sarik.dev.foodwaveproject.enums.OrderStatus;

@Getter
@Setter
public class OrderHistoryUpdateDto {

    @NotNull(message = "Order History ID is required") // Order History ID majburiy
    private Long orderHistoryId;

    @NotNull(message = "Order status is required") // Buyurtma holati yangilanishi majburiy
    private OrderStatus status; // Buyurtma holati (PENDING, COMPLETED, CANCELED)

    private String comments; // Qo'shimcha izoh yoki sabab
}
