package sarik.dev.foodwaveproject.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwaveproject.dto.category.CategoryCreateDto;
import sarik.dev.foodwaveproject.dto.category.CategoryResponseDto;
import sarik.dev.foodwaveproject.dto.category.CategoryUpdateDto;
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
    public CategoryResponseDto createCategory(CategoryCreateDto categoryCreateDTO) {
        Category category = new Category();
        category.setCategoryName(categoryCreateDTO.getCategoryName());
        return mapToDTO(categoryRepository.save(category));
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDto getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryResponseDto updateCategory(Long id, CategoryUpdateDto categoryUpdateDTO) {
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

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDto getCategoryByName(String name) {
        return categoryRepository.findByCategoryName(name)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with name: " + name));
    }

    private CategoryResponseDto mapToDTO(Category category) {
        return new CategoryResponseDto(category.getCategoryId(), category.getCategoryName());
    }
}