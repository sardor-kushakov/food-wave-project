package sarik.dev.foodwaveproject.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import sarik.dev.foodwaveproject.dto.categoryDto.CategoryResponseDTO;
import sarik.dev.foodwaveproject.dto.productDto.ProductResponseDto;
import sarik.dev.foodwaveproject.dto.productDto.UpdateDiscountProductDto;
import sarik.dev.foodwaveproject.dto.productDto.UpdateIsPresentProductDto;
import sarik.dev.foodwaveproject.entity.Product;
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

    @Transactional
    @PostMapping()
    public ResponseEntity<ProductResponseDto> addProduct(@Valid @RequestBody CreateProductDto dto) {
        CategoryResponseDTO category = categoryService.getCategoryByName(dto.getCategory().getName());
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
        Product product = productMapper.toProduct(dto);
        product.setCategory(categoryMapper.toCategory(category));
        productService.createProduct(product);
        return new ResponseEntity<>(productMapper.toProductResponseDto(product), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        Product productById = productService.getProductById(id);
        if (productById == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return new ResponseEntity<>(productMapper.toProductResponseDto(productById), HttpStatus.OK);
    }


    @GetMapping("/{categoryName}/products")
    public ResponseEntity<List<ProductResponseDto>> getProductsByCategory(@PathVariable String categoryName) {
        List<Product> products = productService.getProductsByCategoryName(categoryName);
        if (products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products found for the specified category");
        }
        return new ResponseEntity<>(productMapper.toProductResponseDtoList(products), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody CreateProductDto dto) {
        Product product = productService.getProductById(id);
        if (product.isPresent()) {
            productService.updateProduct(dto, product);
            return new ResponseEntity<>(productMapper.toProductResponseDto(product), HttpStatus.OK);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

    //    @PutMapping("/{id}/isPresent")
//    public ResponseEntity<ProductResponseDto> updateProductIsPresent(@PathVariable Long id, @RequestBody UpdateIsPresentProductDto dto) {
//        Product product = productService.getProductById(id);
//        Product product1 = productService.updateProductIsPresent(dto, product);
//        return new ResponseEntity<>(productMapper.toProductResponseDto(product1), HttpStatus.OK);
//    }
    @PutMapping("/{id}/isPresent")
    public ResponseEntity<ProductResponseDto> updateProductIsPresent(@PathVariable Long id, @RequestBody UpdateIsPresentProductDto dto) {
        Product product = productService.getProductById(id);
        Product updatedProduct = productService.updateProductIsPresent(dto, product);
        return new ResponseEntity<>(productMapper.toProductResponseDto(updatedProduct), HttpStatus.OK);
    }

    @PutMapping("/{id}/discount")
    public ResponseEntity<ProductResponseDto> updateProductDiscount(@PathVariable Long id, @RequestBody UpdateDiscountProductDto dto) {
        Product product = productService.getProductById(id);
        Product updatedProduct = productService.updateProductDiscount(dto, product);
        return new ResponseEntity<>(productMapper.toProductResponseDto(updatedProduct), HttpStatus.OK);
    }
}

