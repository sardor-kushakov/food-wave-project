package sarik.dev.foodwaveproject.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record OtpRequest(
        @Email
        String email,

        @Column(nullable = false)
        @NotBlank
        String otpCode) implements Serializable {
}