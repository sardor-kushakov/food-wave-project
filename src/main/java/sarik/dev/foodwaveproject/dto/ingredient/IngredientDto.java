package sarik.dev.foodwaveproject.dto.ingredient;

import java.io.Serializable;

public record IngredientDto(
        Long id,
        String name,
        Double quantity,
        String unit) implements Serializable {
}
