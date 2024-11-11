package sarik.dev.foodwaveproject.dto.otp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpDto {

    private Long otpId; // OTP ID

    private String code; // OTP kodi

    private String expirationTime; // OTP amal qilish muddati

    private Boolean isUsed; // OTP ishlatilganligi

    private Long userId; // Foydalanuvchi ID

    private Integer attempts; // Urinishlar soni

    private String createdTime; // OTP yaratilgan vaqti
}
