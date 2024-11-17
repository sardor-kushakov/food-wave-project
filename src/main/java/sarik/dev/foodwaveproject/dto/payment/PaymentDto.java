package sarik.dev.foodwaveproject.dto.payment;

import sarik.dev.foodwaveproject.dto.order.OrderDto;
import sarik.dev.foodwaveproject.enums.PaymentMethod;
import sarik.dev.foodwaveproject.enums.PaymentStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

public record PaymentDto(
        Long id,
        OrderDto order,
        Long amount,
        LocalDateTime paymentTime,
        PaymentStatus status,
        PaymentMethod paymentMethod) implements Serializable {
}
