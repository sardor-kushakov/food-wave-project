package sarik.dev.foodwaveproject.dto.auth.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record AuthRoleCreateDto(
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        String name,

        @NotBlank(message = "Description cannot be blank")
        @Size(min = 10, max = 200, message = "Description must be between 10 and 200 characters")
        String description) implements Serializable {
}
