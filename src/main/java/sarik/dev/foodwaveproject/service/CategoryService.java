package sarik.dev.foodwaveproject.service;




import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryCreateDTO categoryCreateDTO);

    CategoryResponseDTO getCategoryById(Long id);

    List<CategoryResponseDTO> getAllCategories();

    CategoryResponseDTO updateCategory(Long id, CategoryUpdateDTO categoryUpdateDTO);

    void deleteCategory(Long id);
}