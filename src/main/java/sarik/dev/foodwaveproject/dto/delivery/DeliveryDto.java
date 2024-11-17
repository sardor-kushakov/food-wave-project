package sarik.dev.foodwaveproject.dto.delivery;

import sarik.dev.foodwaveproject.dto.address.AddressDto;
import sarik.dev.foodwaveproject.dto.auth.user.AuthUserDto;
import sarik.dev.foodwaveproject.dto.order.OrderDto;
import sarik.dev.foodwaveproject.enums.DeliveryStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

public record DeliveryDto(
        Long id,
        LocalDateTime scheduledDeliveryTime,
        LocalDateTime actualDeliveryTime,
        DeliveryStatus status,
        AuthUserDto courier,
        AddressDto deliveryAddress,
        OrderDto order) implements Serializable {
}
