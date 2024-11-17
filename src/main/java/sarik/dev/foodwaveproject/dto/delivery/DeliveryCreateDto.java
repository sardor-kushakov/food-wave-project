package sarik.dev.foodwaveproject.dto.delivery;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import sarik.dev.foodwaveproject.enums.DeliveryStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

public record DeliveryCreateDto(
        @NotNull(message = "Scheduled delivery time cannot be null")
        @Future(message = "Scheduled delivery time must be in the future")
        LocalDateTime scheduledDeliveryTime,

        @NotNull(message = "Delivery address ID cannot be null")
        Long deliveryAddressId,

        @NotNull(message = "Order ID cannot be null")
        Long orderId,

        Long courierId,
        DeliveryStatus status) implements Serializable {
}
