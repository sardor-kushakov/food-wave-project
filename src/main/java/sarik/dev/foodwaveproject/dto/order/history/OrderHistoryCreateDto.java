package sarik.dev.foodwaveproject.dto.order.history;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import sarik.dev.foodwaveproject.enums.OrderStatus;

import java.io.Serializable;

public record OrderHistoryCreateDto(
        @NotNull(message = "Status cannot be null")
        OrderStatus status,

        @Size(max = 200, message = "Comments cannot exceed 200 characters")
        String comments) implements Serializable {
}