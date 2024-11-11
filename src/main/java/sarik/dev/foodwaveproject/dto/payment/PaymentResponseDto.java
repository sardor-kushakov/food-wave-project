package sarik.dev.foodwaveproject.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDto {

    private Long paymentId; // To'lov ID

    private String message; // Foydalanuvchiga yuborilgan xabar

    private Boolean isSuccess; // To'lov muvaffaqiyatli yoki muvaffaqiyatsiz bo'lgani haqida ma'lumot
}
