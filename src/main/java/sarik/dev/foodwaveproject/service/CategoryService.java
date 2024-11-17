package sarik.dev.foodwaveproject.service;

import sarik.dev.foodwaveproject.dto.category.CategoryCreateDto;
import sarik.dev.foodwaveproject.dto.category.CategoryDto;
import sarik.dev.foodwaveproject.dto.category.CategoryResponseDto;
import sarik.dev.foodwaveproject.dto.category.CategoryUpdateDto;

import java.util.List;

public interface CategoryService {

    // Yangi kategoriya yaratish
    CategoryResponseDto create(CategoryCreateDto createDto);

    // ID bo'yicha kategoriya olish
    CategoryDto getById(Long id);

    // Nom bo'yicha kategoriya olish
    CategoryDto getByName(String name);

    // Barcha kategoriyalarni olish
    List<CategoryResponseDto> getAll();

    // Faol kategoriyalarni olish
    List<CategoryResponseDto> getActiveCategories();

    // Faol bo'lmagan kategoriyalarni olish
    List<CategoryResponseDto> getInactiveCategories();

    // Kategoriyani yangilash
    CategoryResponseDto update(Long id, CategoryUpdateDto updateDto);

    // Kategoriyani o'chirish
    void delete(Long id);
}
