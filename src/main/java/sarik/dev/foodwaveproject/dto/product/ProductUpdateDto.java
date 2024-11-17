package sarik.dev.foodwaveproject.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record ProductUpdateDto(
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        String name,

        @Size(max = 150, message = "Description cannot exceed 150 characters")
        String description,

        @NotNull(message = "Price cannot be null")
        @Positive(message = "Price must be positive")
        Long price,

        @NotNull(message = "Availability status cannot be null")
        Boolean available) implements Serializable {
}
