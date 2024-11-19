package sarik.dev.foodwaveproject.dto.order.history;

import sarik.dev.foodwaveproject.enums.OrderStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

public record OrderHistoryResponseDto(
        Long id,
        Long orderId,
        OrderStatus status,
        LocalDateTime changeTime,
        String comments) implements Serializable {
}