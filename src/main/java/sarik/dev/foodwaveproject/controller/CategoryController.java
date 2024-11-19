package sarik.dev.foodwaveproject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sarik.dev.foodwaveproject.dto.categoryDto.CategoryCreateDTO;
import sarik.dev.foodwaveproject.dto.categoryDto.CategoryResponseDTO;
import sarik.dev.foodwaveproject.dto.categoryDto.CategoryUpdateDTO;
import sarik.dev.foodwaveproject.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryCreateDTO categoryCreateDTO) {
        CategoryResponseDTO createdCategory = categoryService.createCategory(categoryCreateDTO);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        CategoryResponseDTO category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<CategoryResponseDTO> getCategoryByName(@PathVariable String name) {
        CategoryResponseDTO category = categoryService.getCategoryByName(name);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<CategoryResponseDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryUpdateDTO categoryUpdateDTO) {
        CategoryResponseDTO updatedCategory = categoryService.updateCategory(id, categoryUpdateDTO);
        return ResponseEntity.ok(updatedCategory);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}