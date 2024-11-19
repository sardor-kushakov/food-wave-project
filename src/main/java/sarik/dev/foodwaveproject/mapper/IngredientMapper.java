package sarik.dev.foodwaveproject.mapper;

import org.mapstruct.Mapper;
import sarik.dev.foodwaveproject.dto.ingredient.IngredientDto;
import sarik.dev.foodwaveproject.entity.Ingredients;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    Ingredients toIngredients(IngredientDto dto);
}
