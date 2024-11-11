package sarik.dev.foodwaveproject.dto.otp;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OtpCreateDto {

    @NotNull(message = "Code is required") // OTP kodi majburiy
    @Size(min = 6, max = 6, message = "Code must be 6 characters long") // OTP kodi uzunligi 6 xonali bo'lishi kerak
    private String code; // OTP kodi

    @NotNull(message = "Expiration time is required") // Amal qilish muddati majburiy
    private LocalDateTime expirationTime; // OTP amal qilish muddati

    @NotNull(message = "User ID is required") // User ID majburiy
    private Long userId; // Foydalanuvchi ID

    @NotNull(message = "Attempts must be 0 at creation") // Urinishlar soni yaratishda 0 bo'lishi kerak
    private Integer attempts = 0; // OTP tekshirishga bo'lgan urinishlar soni
}
