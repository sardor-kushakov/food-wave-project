package sarik.dev.foodwaveproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import sarik.dev.foodwaveproject.dto.category.CategoryCreateDto;
import sarik.dev.foodwaveproject.dto.category.CategoryDto;
import sarik.dev.foodwaveproject.dto.category.CategoryResponseDto;
import sarik.dev.foodwaveproject.dto.category.CategoryUpdateDto;
import sarik.dev.foodwaveproject.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    // CategoryCreateDto -> Category
    @Mapping(target = "id", ignore = true)
    Category fromCreateDto(CategoryCreateDto dto);

    // Category -> CategoryDto
    @Mapping(target = "products", source = "products")
    CategoryDto toDto(Category category);

    // Category -> CategoryResponseDto
    @Mapping(target = "products", source = "products")
    CategoryResponseDto toResponseDto(Category category);

    // CategoryUpdateDto -> Category
    @Mapping(target = "id", ignore = true)
    Category fromUpdateDto(CategoryUpdateDto dto, @MappingTarget Category category);
}
