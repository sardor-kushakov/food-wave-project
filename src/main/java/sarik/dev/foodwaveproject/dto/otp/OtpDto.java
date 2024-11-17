package sarik.dev.foodwaveproject.dto.otp;

import sarik.dev.foodwaveproject.dto.auth.user.AuthUserDto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record OtpDto(
        Long id,
        String code,
        LocalDateTime createdTime,
        LocalDateTime expirationTime,
        boolean isUsed,
        AuthUserDto recipient) implements Serializable {
}
