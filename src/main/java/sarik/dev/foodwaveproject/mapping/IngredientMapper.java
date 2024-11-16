package sarik.dev.foodwaveproject.mapping;

import org.mapstruct.Mapper;
import sarik.dev.foodwaveproject.dto.ingredient.IngredientDto;
import sarik.dev.foodwaveproject.entity.Ingredient;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    Ingredient toIngredients(IngredientDto dto);
}
