package sarik.dev.foodwaveproject.dto.order.item;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public record OrderItemCreateDto(
        @NotNull(message = "Product ID cannot be null")
        Long productId,

        @Positive(message = "Quantity must be greater than 0")
        Double quantity,

        @Positive(message = "Unit price must be positive")
        Long unitPrice) implements Serializable {
}
