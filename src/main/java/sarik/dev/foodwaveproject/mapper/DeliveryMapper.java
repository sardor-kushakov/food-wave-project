package sarik.dev.foodwaveproject.mapper;

import org.mapstruct.Mapper;
import sarik.dev.foodwaveproject.dto.delivery.DeliveryCreateDto;
import sarik.dev.foodwaveproject.dto.delivery.DeliveryDto;
import sarik.dev.foodwaveproject.dto.delivery.DeliveryResponseDto;
import sarik.dev.foodwaveproject.dto.delivery.DeliveryUpdateDto;
import sarik.dev.foodwaveproject.entity.Delivery;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    // DeliveryCreateDto -> Delivery
    Delivery fromCreateDto(DeliveryCreateDto dto);

    // Delivery -> DeliveryDto
    DeliveryDto toDto(Delivery delivery);

    // Delivery -> DeliveryResponseDto
    DeliveryResponseDto toResponseDto(Delivery delivery);

    // DeliveryUpdateDto -> Delivery
    Delivery fromUpdateDto(DeliveryUpdateDto dto, Delivery delivery);
}
