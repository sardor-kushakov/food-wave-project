package sarik.dev.foodwaveproject.dto.order;

import sarik.dev.foodwaveproject.dto.order.item.OrderItemResponseDto;
import sarik.dev.foodwaveproject.enums.OrderStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDto(
        Long id,
        Long customerId,
        String customerName,
        LocalDateTime orderDate,
        Long totalAmount,
        OrderStatus status,
        List<OrderItemResponseDto> orderItems) implements Serializable {
}
