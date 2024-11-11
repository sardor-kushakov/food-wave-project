package sarik.dev.foodwaveproject.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentValidDto {

    private Long paymentId; // To'lov ID

    private Long amount; // To'lov miqdori

    private String status; // To'lov holati

    public boolean isValid() {
        return amount > 0 && (status.equals("PENDING") || status.equals("COMPLETED") || status.equals("FAILED"));
    }
}
