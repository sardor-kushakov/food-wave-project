package sarik.dev.foodwaveproject.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwaveproject.dto.categoryDto.CategoryCreateDTO;
import sarik.dev.foodwaveproject.dto.categoryDto.CategoryResponseDTO;
import sarik.dev.foodwaveproject.dto.categoryDto.CategoryUpdateDTO;
import sarik.dev.foodwaveproject.entity.Category;
import sarik.dev.foodwaveproject.exception.ResourceNotFoundException;
import sarik.dev.foodwaveproject.repository.CategoryRepository;
import sarik.dev.foodwaveproject.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public CategoryResponseDTO createCategory(CategoryCreateDTO categoryCreateDTO) {
        Category category = new Category();
        category.setCategoryName(categoryCreateDTO.getCategoryName());
        return mapToDTO(categoryRepository.save(category));
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDTO getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryResponseDTO updateCategory(Long id, CategoryUpdateDTO categoryUpdateDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        category.setCategoryName(categoryUpdateDTO.getCategoryName());
        return mapToDTO(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    private CategoryResponseDTO mapToDTO(Category category) {
        return new CategoryResponseDTO(category.getCategoryId(), category.getCategoryName());
    }
}