package sarik.dev.foodwaveproject.dto.request.auth;

import jakarta.validation.constraints.NotBlank;

public record OtpRequest(
        @NotBlank(message = "OTP code cannot be blank")
        String code,

        @NotBlank(message = "Email cannot be blank")
        String email) {
}
