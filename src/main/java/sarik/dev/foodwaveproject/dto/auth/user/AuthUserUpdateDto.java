package sarik.dev.foodwaveproject.dto.auth.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record AuthUserUpdateDto(
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        String name,

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Email should be valid")
        String email) implements Serializable {
}
