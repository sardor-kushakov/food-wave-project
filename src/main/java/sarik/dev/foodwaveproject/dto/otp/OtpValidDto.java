package sarik.dev.foodwaveproject.dto.otp;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OtpValidDto {

    @NotNull(message = "OTP code is required") // OTP kodi majburiy
    private String code; // OTP kodi

    @NotNull(message = "User ID is required") // User ID majburiy
    private Long userId; // Foydalanuvchi ID

    public boolean isExpired(LocalDateTime expirationTime) {
        return LocalDateTime.now().isAfter(expirationTime); // OTP muddati tugagandami?
    }

    public boolean isInvalid(Integer attempts, Boolean isUsed, LocalDateTime expirationTime) {
        return isUsed || isExpired(expirationTime) || attempts >= 3; // OTP faqat ishlatilgan, amal qilish muddati o'tgan yoki urinishlar limitiga yetgan bo'lsa invalid hisoblanadi
    }
}
