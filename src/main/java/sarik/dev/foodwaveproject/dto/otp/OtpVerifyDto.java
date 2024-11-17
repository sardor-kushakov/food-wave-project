package sarik.dev.foodwaveproject.dto.otp;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record OtpVerifyDto(
        @NotNull(message = "Code cannot be null")
        @Size(min = 6, max = 6, message = "Code must be exactly 6 characters")
        String code,

        @NotNull(message = "Recipient user ID cannot be null")
        Long recipientId) implements Serializable {
}
