package sarik.dev.foodwaveproject.mapper;

import org.mapstruct.Mapper;
import sarik.dev.foodwaveproject.dto.ingredient.IngredientCreateDto;
import sarik.dev.foodwaveproject.dto.ingredient.IngredientDto;
import sarik.dev.foodwaveproject.dto.ingredient.IngredientResponseDto;
import sarik.dev.foodwaveproject.dto.ingredient.IngredientUpdateDto;
import sarik.dev.foodwaveproject.entity.Ingredient;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    // IngredientCreateDto -> Ingredient
    Ingredient fromCreateDto(IngredientCreateDto dto);

    // Ingredient -> IngredientDto
    IngredientDto toDto(Ingredient ingredient);

    // Ingredient -> IngredientResponseDto
    IngredientResponseDto toResponseDto(Ingredient ingredient);

    // IngredientUpdateDto -> Ingredient
    Ingredient fromUpdateDto(IngredientUpdateDto dto, Ingredient ingredient);
}
