package sarik.dev.foodwaveproject.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sarik.dev.foodwaveproject.dto.categoryDto.CategoryResponseDTO;
import sarik.dev.foodwaveproject.dto.productDto.CreateProductDto;
import sarik.dev.foodwaveproject.dto.productDto.ProductResponseDto;
import sarik.dev.foodwaveproject.dto.productDto.UpdateDiscountProductDto;
import sarik.dev.foodwaveproject.dto.productDto.UpdateIsPresentProductDto;
import sarik.dev.foodwaveproject.entity.Category;
import sarik.dev.foodwaveproject.entity.Product;
import sarik.dev.foodwaveproject.exception.ResourceNotFoundException;
import sarik.dev.foodwaveproject.mapping.CategoryMapper;
import sarik.dev.foodwaveproject.mapping.ProductMapper;
import sarik.dev.foodwaveproject.repository.CategoryRepository;
import sarik.dev.foodwaveproject.repository.OrderItemRepository;
import sarik.dev.foodwaveproject.repository.ProductRepository;
import sarik.dev.foodwaveproject.service.CategoryService;
import sarik.dev.foodwaveproject.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;
    private final OrderItemRepository orderItemRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, CategoryServiceImpl categoryService, ProductMapper productMapper, CategoryMapper categoryMapper, CategoryService categoryService1, OrderItemRepository orderItemRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
        this.categoryService = categoryService1;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public ProductResponseDto createProduct(CreateProductDto dto) {
        CategoryResponseDTO category = categoryService.getCategoryByName(dto.getCategory().getName());
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
        Product product = productMapper.toProduct(dto);
        product.setCategory(categoryMapper.toCategory(category));
        productRepository.save(product);
        return productMapper.toProductResponseDto(product);
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(CreateProductDto dto, Product product) {
        Category category = categoryRepository.findByCategoryName(dto.getCategory().getName())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with name: " + dto.getCategory().getName()));

        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        product.setImage(dto.getImage());
        product.setDescription(dto.getDescription());
        product.setIngredients(dto.getIngredients());
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public Product updateProductIsPresent(UpdateIsPresentProductDto dto, Product product) {
        if (dto == null || product == null) {
            throw new IllegalArgumentException("Invalid input data");
        }
        product.setPresent(dto.isPresent());
        return productRepository.save(product);
    }

    @Override
    public Product updateProductDiscount(UpdateDiscountProductDto dto, Product product) {
        if (dto == null || product == null) {
            throw new IllegalArgumentException("Invalid input data");
        }
        if (product.getPrice()<dto.getDiscount()) {
            throw new IllegalArgumentException("Discount must be small than product price");
        }
        product.setDiscount(dto.getDiscount());
        return productRepository.save(product);
    }


    @Override
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDto> getProductsByCategoryName(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with name: " + categoryName));

        List<Product> products = productRepository.findByCategory(category);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No products found for category: " + categoryName);
        }

        return productMapper.toProductResponseDtoList(products);
    }

    @Override
    public List<ProductResponseDto> getProductsByPopularity() {
        // Popularlik bo'yicha ma'lumotlarni olish
        List<Object[]> popularityData = orderItemRepository.findProductsByPopularity();

        // Har bir productId uchun ProductResponseDto ni yaratish
        return popularityData.stream()
                .map(data -> {
                    Long productId = (Long) data[0];  // productId ni olish
                    Product product = productRepository.findById(productId).orElse(null);

                    if (product != null) {
                        // ProductResponseDto ni yaratish
                        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                                .id(product.getId())
                                .productName(product.getProductName())
                                .price(product.getPrice())
                                .image(product.getImage())
                                .description(product.getDescription())
                                .ingredients(product.getIngredients())
                                .category(product.getCategory())
                                .isPresent(product.isPresent())
                                .discount(product.getDiscount())
                                .build();

                        return productResponseDto;  // ProductResponseDto qaytarish
                    }
                    return null;  // Agar product mavjud bo'lmasa, null qaytarish
                })
                .filter(productResponseDto -> productResponseDto != null)  // Null qiymatlarni filtrlash
                .collect(Collectors.toList());  // Barcha ProductResponseDto larni ro'yxatga yig'ish
    }
}
