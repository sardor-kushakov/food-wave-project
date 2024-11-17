package sarik.dev.foodwaveproject.dto.ingredient;

import java.io.Serializable;

public record IngredientResponseDto(
        Long id,
        String name,
        Double quantity,
        String unit) implements Serializable {
}
