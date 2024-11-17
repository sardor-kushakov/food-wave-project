package sarik.dev.foodwaveproject.dto.payment;

import jakarta.validation.constraints.NotNull;
import sarik.dev.foodwaveproject.enums.PaymentStatus;

import java.io.Serializable;

public record PaymentUpdateDto(
        @NotNull(message = "Payment ID cannot be null")
        Long paymentId,

        @NotNull(message = "Payment status cannot be null")
        PaymentStatus status) implements Serializable {
}
