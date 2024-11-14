package sarik.dev.foodwaveproject.dto.requestDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link sarik.dev.foodwaveproject.entity.Otp}
 */
@Value
public class OtpRequest implements Serializable {
    @Email
    String email;
    @Column(nullable = false)
    @NotBlank
    String otpCode;
}