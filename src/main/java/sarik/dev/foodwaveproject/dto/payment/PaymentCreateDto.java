package sarik.dev.foodwaveproject.dto.payment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import sarik.dev.foodwaveproject.enums.PaymentMethod;

import java.io.Serializable;

public record PaymentCreateDto(
        @NotNull(message = "Order ID cannot be null")
        Long orderId,

        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Amount must be a positive value")
        Long amount,

        @NotNull(message = "Payment method cannot be null")
        PaymentMethod paymentMethod) implements Serializable {
}
