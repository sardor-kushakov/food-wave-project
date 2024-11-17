package sarik.dev.foodwaveproject.dto.delivery;

import jakarta.validation.constraints.FutureOrPresent;
import sarik.dev.foodwaveproject.enums.DeliveryStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

public record DeliveryUpdateDto(
        @FutureOrPresent(message = "Scheduled delivery time cannot be in the past")
        LocalDateTime scheduledDeliveryTime,

        LocalDateTime actualDeliveryTime,

        DeliveryStatus status,

        Long courierId) implements Serializable {
}
