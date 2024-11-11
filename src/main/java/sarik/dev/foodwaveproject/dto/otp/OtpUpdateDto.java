package sarik.dev.foodwaveproject.dto.otp;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpUpdateDto {

    @NotNull(message = "OTP ID is required") // OTP ID majburiy
    private Long otpId; // OTP ID

    private Boolean isUsed; // OTP ishlatilganligini belgilash

    private Integer attempts; // Urinishlar sonini yangilash
}
