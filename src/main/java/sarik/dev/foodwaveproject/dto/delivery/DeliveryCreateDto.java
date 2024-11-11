package sarik.dev.foodwaveproject.dto.delivery;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import sarik.dev.foodwaveproject.enums.DeliveryStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class DeliveryCreateDto {

    @NotNull(message = "Order ID is required") // Buyurtma ID majburiy
    private Long orderId;

    @NotNull(message = "Address ID is required") // Yetkazib berish manzili majburiy
    private Long addressId;

    @Future(message = "Delivery date must be in the future") // Yetkazib berish sanasi kelajakda bo'lishi kerak
    private LocalDateTime deliveryDate;

    @NotNull(message = "Delivery status is required") // Yetkazib berish holati majburiy
    private DeliveryStatus status;

    @NotNull(message = "Courier ID is required") // Yetkazuvchi ID majburiy
    private Long courierId;
}
