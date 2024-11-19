package sarik.dev.foodwaveproject.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import sarik.dev.foodwaveproject.dto.productDto.CreateProductDto;
import sarik.dev.foodwaveproject.dto.productDto.ProductResponseDto;
import sarik.dev.foodwaveproject.dto.productDto.UpdateDiscountProductDto;
import sarik.dev.foodwaveproject.dto.productDto.UpdateIsPresentProductDto;
import sarik.dev.foodwaveproject.entity.Product;
import sarik.dev.foodwaveproject.exception.ResourceNotFoundException;
import sarik.dev.foodwaveproject.mapping.CategoryMapper;
import sarik.dev.foodwaveproject.mapping.IngredientMapper;
import sarik.dev.foodwaveproject.mapping.ProductMapper;
import sarik.dev.foodwaveproject.repository.ProductRepository;
import sarik.dev.foodwaveproject.service.CategoryService;
import sarik.dev.foodwaveproject.service.ProductService;
import sarik.dev.foodwaveproject.service.impl.ProductServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Product API", description = "API for product")
@RequestMapping("api/product")
public class ProductController {
    private final ProductMapper productMapper;
    private final ProductService productService;
    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    public ProductController(ProductMapper productMapper, ProductRepository productRepository, ProductServiceImpl productServiceImpl, ProductService productService, CategoryMapper categoryMapper, IngredientMapper ingredientMapper, CategoryService categoryService) {
        this.productMapper = productMapper;
        this.productService = productService;
        this.categoryMapper = categoryMapper;
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Transactional
    public ResponseEntity<ProductResponseDto> addProduct(@Valid @RequestBody CreateProductDto dto) {
        ProductResponseDto createdProduct = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(value -> ResponseEntity.ok(productMapper.toProductResponseDto(value)))
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
    }

    @GetMapping("/{categoryName}/products")
    public ResponseEntity<List<ProductResponseDto>> getProductsByCategory(@PathVariable String categoryName) {
        List<ProductResponseDto> products = productService.getProductsByCategoryName(categoryName);
        return ResponseEntity.ok(products);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody CreateProductDto dto) {
        Product existingProduct = productService.getProductById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        Product updatedProduct = productService.updateProduct(dto, existingProduct);
        return ResponseEntity.ok(productMapper.toProductResponseDto(updatedProduct));
    }

    @PutMapping("/{id}/isPresent")
    public ResponseEntity<ProductResponseDto> updateProductIsPresent(
            @PathVariable Long id, @RequestBody UpdateIsPresentProductDto dto) {
        Product existingProduct = productService.getProductById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        Product updatedProduct = productService.updateProductIsPresent(dto, existingProduct);
        return ResponseEntity.ok(productMapper.toProductResponseDto(updatedProduct));
    }

    @PutMapping("/{id}/discount")
    public ResponseEntity<ProductResponseDto> updateProductDiscount(
            @PathVariable Long id, @RequestBody UpdateDiscountProductDto dto) {
        Product existingProduct = productService.getProductById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        Product updatedProduct = productService.updateProductDiscount(dto, existingProduct);
        return ResponseEntity.ok(productMapper.toProductResponseDto(updatedProduct));
    }
    @GetMapping("/popularity")
    public ResponseEntity<List<ProductResponseDto>> getPopularProducts() {
        // ProductService orqali eng mashhur mahsulotlarni olish
        List<ProductResponseDto> popularProducts = productService.getProductsByPopularity();

        // Agar ro'yxatda mahsulotlar bo'lsa, 200 OK statusi bilan qaytarish
        if (popularProducts != null && !popularProducts.isEmpty()) {
            return ResponseEntity.ok(popularProducts);
        }

        // Agar ro'yxat bo'sh bo'lsa, 404 Not Found statusi bilan qaytarish
        return ResponseEntity.notFound().build();
    }
}

