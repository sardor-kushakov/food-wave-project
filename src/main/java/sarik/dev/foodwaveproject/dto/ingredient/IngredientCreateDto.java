package sarik.dev.foodwaveproject.dto.ingredient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientCreateDto {

    @NotBlank(message = "Name is required") // Ingredient nomi majburiy
    private String name;

    @NotNull(message = "Quantity is required") // Miqdor majburiy
    @Positive(message = "Quantity must be positive") // Miqdor musbat bo'lishi kerak
    private Double quantity;

    @NotBlank(message = "Unit is required") // O'lchov birligi majburiy
    private String unit;
}
