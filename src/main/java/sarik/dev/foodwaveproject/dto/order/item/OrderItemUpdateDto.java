package sarik.dev.foodwaveproject.dto.order.item;

import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public record OrderItemUpdateDto(
        @Positive(message = "Quantity must be greater than 0")
        Double quantity,

        @Positive(message = "Unit price must be positive")
        Long unitPrice) implements Serializable {
}
