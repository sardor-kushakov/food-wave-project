package sarik.dev.foodwaveproject.mapper;

import org.mapstruct.Mapper;
import sarik.dev.foodwaveproject.dto.order.item.OrderItemCreateDto;
import sarik.dev.foodwaveproject.dto.order.item.OrderItemDto;
import sarik.dev.foodwaveproject.dto.order.item.OrderItemResponseDto;
import sarik.dev.foodwaveproject.dto.order.item.OrderItemUpdateDto;
import sarik.dev.foodwaveproject.entity.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    // OrderItemCreateDto -> OrderItem
    OrderItem fromCreateDto(OrderItemCreateDto dto);

    // OrderItem -> OrderItemDto
    OrderItemDto toDto(OrderItem orderItem);

    // OrderItem -> OrderItemResponseDto
    OrderItemResponseDto toResponseDto(OrderItem orderItem);

    // OrderItemUpdateDto -> OrderItem
    OrderItem fromUpdateDto(OrderItemUpdateDto dto, OrderItem orderItem);
}
