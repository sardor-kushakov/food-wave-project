package sarik.dev.foodwaveproject.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record CategoryCreateDto(
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        String name,

        @NotBlank(message = "Description cannot be blank")
        @Size(max = 200, message = "Description cannot exceed 200 characters")
        String description,

        boolean isActive) implements Serializable {
}
