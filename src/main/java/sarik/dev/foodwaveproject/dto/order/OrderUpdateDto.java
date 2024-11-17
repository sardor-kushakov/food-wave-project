package sarik.dev.foodwaveproject.dto.order;

import jakarta.validation.constraints.NotNull;
import sarik.dev.foodwaveproject.enums.OrderStatus;

import java.io.Serializable;

public record OrderUpdateDto(
        @NotNull(message = "Order ID cannot be null")
        Long orderId,

        OrderStatus status) implements Serializable {
}
