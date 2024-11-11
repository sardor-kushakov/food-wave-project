package sarik.dev.foodwaveproject.dto.delivery;

import lombok.Getter;
import lombok.Setter;
import sarik.dev.foodwaveproject.enums.DeliveryStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class DeliveryDto {

    private Long deliveryId; // Yetkazib berish ID

    private Long orderId; // Buyurtma ID (Yetkazib berilayotgan buyurtma)

    private Long addressId; // Yetkazib berish manzili ID

    private LocalDateTime deliveryDate; // Yetkazib berish sanasi

    private DeliveryStatus status; // Yetkazib berish holati

    private Long courierId; // Yetkazuvchi ID
}
