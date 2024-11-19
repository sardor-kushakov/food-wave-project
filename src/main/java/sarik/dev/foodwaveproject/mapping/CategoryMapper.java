package sarik.dev.foodwaveproject.mapping;

import org.mapstruct.Mapper;
import sarik.dev.foodwaveproject.dto.category.CategoryDto;
import sarik.dev.foodwaveproject.dto.category.CategoryResponseDto;
import sarik.dev.foodwaveproject.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryDto dto);
    Category toCategory(CategoryResponseDto dto);
}
