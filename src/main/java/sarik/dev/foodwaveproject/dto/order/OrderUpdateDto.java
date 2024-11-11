package sarik.dev.foodwaveproject.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import sarik.dev.foodwaveproject.enums.OrderStatus;

@Getter
@Setter
public class OrderUpdateDto {

    @NotNull(message = "Order ID is required") // Buyurtma ID majburiy
    private Long orderId;

    @NotNull(message = "Order status is required") // Buyurtma holatini yangilash majburiy
    private OrderStatus status; // Buyurtma holati (PENDING, COMPLETED, CANCELED)

    @NotNull(message = "Total amount is required") // Buyurtma summasini yangilash majburiy
    private Long totalAmount; // Buyurtmaning umumiy summasi
}
