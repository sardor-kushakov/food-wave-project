package sarik.dev.foodwaveproject.dto.ingredient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientDto {

    private Long ingredientId; // Ingredient ID

    private String name; // Ingredient nomi

    private Double quantity; // Miqdor

    private String unit; // O'lchov birligi
}
