package sarik.dev.foodwaveproject.service;

import sarik.dev.foodwaveproject.dto.category.CategoryCreateDto;
import sarik.dev.foodwaveproject.dto.category.CategoryResponseDto;
import sarik.dev.foodwaveproject.dto.category.CategoryUpdateDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryCreateDto categoryCreateDTO);

    CategoryResponseDto getCategoryById(Long id);

    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto updateCategory(Long id, CategoryUpdateDto categoryUpdateDTO);

    void deleteCategory(Long id);

    CategoryResponseDto getCategoryByName(String name);
}
