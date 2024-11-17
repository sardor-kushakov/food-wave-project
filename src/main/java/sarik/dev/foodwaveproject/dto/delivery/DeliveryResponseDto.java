package sarik.dev.foodwaveproject.dto.delivery;

import sarik.dev.foodwaveproject.dto.address.AddressResponseDto;
import sarik.dev.foodwaveproject.dto.auth.user.AuthUserResponseDto;
import sarik.dev.foodwaveproject.dto.order.OrderResponseDto;
import sarik.dev.foodwaveproject.enums.DeliveryStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

public record DeliveryResponseDto(
        Long id,
        LocalDateTime scheduledDeliveryTime,
        LocalDateTime actualDeliveryTime,
        DeliveryStatus status,
        AuthUserResponseDto courier,
        AddressResponseDto deliveryAddress,
        OrderResponseDto order) implements Serializable {
}
