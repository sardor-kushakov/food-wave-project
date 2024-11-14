package sarik.dev.foodwaveproject.service;


import sarik.dev.foodwaveproject.dto.categoryDto.CategoryCreateDTO;
import sarik.dev.foodwaveproject.dto.categoryDto.CategoryResponseDTO;
import sarik.dev.foodwaveproject.dto.categoryDto.CategoryUpdateDTO;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryCreateDTO categoryCreateDTO);

    CategoryResponseDTO getCategoryById(Long id);

    List<CategoryResponseDTO> getAllCategories();

    CategoryResponseDTO updateCategory(Long id, CategoryUpdateDTO categoryUpdateDTO);

    void deleteCategory(Long id);

    CategoryResponseDTO getCategoryByName(String name);
}
