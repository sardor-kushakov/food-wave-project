package sarik.dev.foodwaveproject.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentUpdateDto {

    private Long paymentId; // To'lov ID

    private String status; // To'lov holati (PENDING, COMPLETED, FAILED)

    private Long amount; // Yangilangan to'lov miqdori
}
