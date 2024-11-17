package sarik.dev.foodwaveproject.mapper;

import org.mapstruct.Mapper;
import sarik.dev.foodwaveproject.dto.order.OrderCreateDto;
import sarik.dev.foodwaveproject.dto.order.OrderDto;
import sarik.dev.foodwaveproject.dto.order.OrderResponseDto;
import sarik.dev.foodwaveproject.dto.order.OrderUpdateDto;
import sarik.dev.foodwaveproject.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    // OrderCreateDto -> Order
    Order fromCreateDto(OrderCreateDto dto);

    // Order -> OrderDto
    OrderDto toDto(Order order);

    // Order -> OrderResponseDto
    OrderResponseDto toResponseDto(Order order);

    // OrderUpdateDto -> Order
    Order fromUpdateDto(OrderUpdateDto dto, Order order);
}
