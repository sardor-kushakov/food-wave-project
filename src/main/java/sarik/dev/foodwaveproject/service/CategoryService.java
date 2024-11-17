package sarik.dev.foodwaveproject.service;




import java.util.List;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryCreateDto categoryCreateDTO);

    CategoryResponseDto getCategoryById(Long id);

    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto updateCategory(Long id, CategoryUpdateDto categoryUpdateDTO);

    void deleteCategory(Long id);

    CategoryResponseDto getCategoryByName(String name);
}
