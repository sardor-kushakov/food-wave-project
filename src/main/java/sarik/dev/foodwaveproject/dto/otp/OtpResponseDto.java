package sarik.dev.foodwaveproject.dto.otp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpResponseDto {

    private Long otpId; // OTP ID

    private String message; // Foydalanuvchiga yuborilgan xabar

    private Boolean isValid; // OTP valid yoki invalid ekanligini ko'rsatish
}
