package sarik.dev.foodwaveproject.dto.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import sarik.dev.foodwaveproject.dto.order.item.OrderItemCreateDto;

import java.io.Serializable;
import java.util.List;

public record OrderCreateDto(
        @NotNull(message = "Total amount cannot be null")
        @Positive(message = "Total amount must be positive")
        Long totalAmount,

        @NotNull(message = "Order items cannot be null")
        List<OrderItemCreateDto> orderItems) implements Serializable {
}
