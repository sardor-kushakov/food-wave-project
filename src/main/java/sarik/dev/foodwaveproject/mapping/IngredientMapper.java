package sarik.dev.foodwaveproject.mapping;

import org.mapstruct.Mapper;
import sarik.dev.foodwaveproject.dto.IngredientDto;
import sarik.dev.foodwaveproject.entity.Ingredients;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    Ingredients toIngredients(IngredientDto dto);
}
