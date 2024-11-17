package sarik.dev.foodwaveproject.dto.order.history;

import sarik.dev.foodwaveproject.enums.OrderStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

public record OrderHistoryDto(
        Long id,
        OrderStatus status,
        LocalDateTime changeTime,
        String comments) implements Serializable {
}
