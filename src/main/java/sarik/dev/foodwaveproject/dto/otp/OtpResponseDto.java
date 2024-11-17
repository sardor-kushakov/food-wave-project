package sarik.dev.foodwaveproject.dto.otp;

import java.io.Serializable;
import java.time.LocalDateTime;

public record OtpResponseDto(
        Long id,
        String code,
        LocalDateTime createdTime,
        LocalDateTime expirationTime,
        boolean isUsed,
        Long recipientId) implements Serializable {
}
