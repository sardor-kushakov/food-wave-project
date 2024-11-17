package sarik.dev.foodwaveproject.mapper;

import org.mapstruct.Mapper;
import sarik.dev.foodwaveproject.dto.order.history.OrderHistoryCreateDto;
import sarik.dev.foodwaveproject.dto.order.history.OrderHistoryDto;
import sarik.dev.foodwaveproject.dto.order.history.OrderHistoryResponseDto;
import sarik.dev.foodwaveproject.entity.OrderHistory;

@Mapper(componentModel = "spring")
public interface OrderHistoryMapper {

    // OrderHistoryCreateDto -> OrderHistory
    OrderHistory fromCreateDto(OrderHistoryCreateDto dto);

    // OrderHistory -> OrderHistoryDto
    OrderHistoryDto toDto(OrderHistory orderHistory);

    // OrderHistory -> OrderHistoryResponseDto
    OrderHistoryResponseDto toResponseDto(OrderHistory orderHistory);
}
