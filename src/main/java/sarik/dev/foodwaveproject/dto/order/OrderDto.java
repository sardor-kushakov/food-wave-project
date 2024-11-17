package sarik.dev.foodwaveproject.dto.order;

import sarik.dev.foodwaveproject.dto.auth.user.AuthUserDto;
import sarik.dev.foodwaveproject.dto.order.item.OrderItemDto;
import sarik.dev.foodwaveproject.enums.OrderStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
        Long id,
        AuthUserDto customer,
        LocalDateTime orderDate,
        Long totalAmount,
        OrderStatus status,
        List<OrderItemDto> orderItems) implements Serializable {
}
