package sarik.dev.foodwaveproject.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {

    private Long paymentId; // To'lov ID

    private Long orderId; // Buyurtma ID

    private Long amount; // To'lov miqdori

    private String paymentDate; // To'lov vaqti

    private String status; // To'lov holati

    private String paymentMethod; // To'lov turi
}
