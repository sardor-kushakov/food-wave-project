package sarik.dev.foodwaveproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwaveproject.dto.category.CategoryCreateDto;
import sarik.dev.foodwaveproject.dto.category.CategoryDto;
import sarik.dev.foodwaveproject.dto.category.CategoryResponseDto;
import sarik.dev.foodwaveproject.dto.category.CategoryUpdateDto;
import sarik.dev.foodwaveproject.entity.Category;
import sarik.dev.foodwaveproject.exception.ResourceNotFoundException;
import sarik.dev.foodwaveproject.mapper.CategoryMapper;
import sarik.dev.foodwaveproject.repository.CategoryRepository;
import sarik.dev.foodwaveproject.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDto create(CategoryCreateDto createDto) {
        Category category = categoryMapper.fromCreateDto(createDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponseDto(savedCategory);
    }

    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "ID", id));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto getByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Name", name));
        return categoryMapper.toDto(category);
    }

    @Override
    public List<CategoryResponseDto> getAll() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("Category", "All", "No categories found");
        }
        return categories.stream()
                .map(categoryMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryResponseDto> getActiveCategories() {
        List<Category> activeCategories = categoryRepository.findByIsActiveTrue();
        if (activeCategories.isEmpty()) {
            throw new ResourceNotFoundException("Category", "Active", "No active categories found");
        }
        return activeCategories.stream()
                .map(categoryMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryResponseDto> getInactiveCategories() {
        List<Category> inactiveCategories = categoryRepository.findByIsActiveFalse();
        if (inactiveCategories.isEmpty()) {
            throw new ResourceNotFoundException("Category", "Inactive", "No inactive categories found");
        }
        return inactiveCategories.stream()
                .map(categoryMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryUpdateDto updateDto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "ID", id));

        categoryMapper.fromUpdateDto(updateDto, existingCategory);

        Category updatedCategory = categoryRepository.save(existingCategory);
        return categoryMapper.toResponseDto(updatedCategory);
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "ID", id));
        categoryRepository.delete(category);
    }
}
