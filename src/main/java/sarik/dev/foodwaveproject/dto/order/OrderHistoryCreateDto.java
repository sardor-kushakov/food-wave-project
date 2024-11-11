package sarik.dev.foodwaveproject.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import sarik.dev.foodwaveproject.enums.OrderStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderHistoryCreateDto {

    @NotNull(message = "Order ID is required") // Buyurtma ID majburiy
    private Long orderId; // Buyurtma ID

    @NotNull(message = "Order status is required") // Buyurtma holati majburiy
    private OrderStatus status; // Buyurtma holati (PENDING, COMPLETED, CANCELED)

    @NotNull(message = "Change date is required") // Holat o'zgargan vaqt majburiy
    private LocalDateTime changeDate = LocalDateTime.now(); // Holat o'zgargan vaqt

    private String comments; // Qo'shimcha izoh yoki sabab
}
