package sarik.dev.foodwaveproject.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import sarik.dev.foodwaveproject.enums.OrderStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderCreateDto {

    @NotNull(message = "User ID is required") // Foydalanuvchi ID majburiy
    private Long userId; // Buyurtmani bergan foydalanuvchi ID

    @NotNull(message = "Order date is required") // Buyurtma berilgan vaqt majburiy
    private LocalDateTime orderDate; // Buyurtma berilgan vaqt

    @NotNull(message = "Total amount is required") // Buyurtmaning umumiy summasi majburiy
    private Long totalAmount; // Buyurtmaning umumiy summasi

    @NotNull(message = "Order status is required") // Buyurtma holati majburiy
    private OrderStatus status = OrderStatus.PENDING; // Buyurtma holati (PENDING, COMPLETED, CANCELED)
}
