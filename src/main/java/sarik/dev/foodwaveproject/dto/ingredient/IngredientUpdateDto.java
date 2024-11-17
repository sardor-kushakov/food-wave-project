package sarik.dev.foodwaveproject.dto.ingredient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record IngredientUpdateDto(
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        String name,

        @NotNull(message = "Quantity cannot be null")
        @Positive(message = "Quantity must be a positive number")
        Double quantity,

        @NotBlank(message = "Unit cannot be blank")
        @Size(max = 20, message = "Unit cannot exceed 20 characters")
        String unit) implements Serializable {
}
