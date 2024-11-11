package sarik.dev.foodwaveproject.dto.payment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentCreateDto {

    @NotNull(message = "Order ID is required") // Buyurtma ID majburiy
    private Long orderId; // Buyurtma ID

    @NotNull(message = "Amount is required") // To'lov miqdori majburiy
    @Positive(message = "Amount must be positive") // To'lov miqdori musbat bo'lishi kerak
    private Long amount; // To'lov miqdori

    @NotNull(message = "Payment method is required") // To'lov turi majburiy
    private String paymentMethod; // To'lov turi (masalan, CREDIT_CARD, CASH, PAYPAL)
}
