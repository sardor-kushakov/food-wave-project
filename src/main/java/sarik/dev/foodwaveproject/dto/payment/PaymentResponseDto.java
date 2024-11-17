package sarik.dev.foodwaveproject.dto.payment;

import sarik.dev.foodwaveproject.enums.PaymentMethod;
import sarik.dev.foodwaveproject.enums.PaymentStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

public record PaymentResponseDto(
        Long id,
        Long orderId,
        Long amount,
        LocalDateTime paymentTime,
        PaymentStatus status,
        PaymentMethod paymentMethod) implements Serializable {
}
