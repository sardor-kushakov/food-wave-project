package sarik.dev.foodwaveproject.service;

import sarik.dev.foodwaveproject.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<ProductResponseDto> getAllProducts();
    Product getProductById(Long id);
    Product updateProduct(CreateProductDto dto, Product product);
    Product updateProductIsPresent(UpdateIsPresentProductDto dto, Product product);
    Product updateProductDiscount(UpdateDiscountProductDto dto, Product product);
    void deleteProductById(Long id);
    List<Product> getProductsByCategoryName(String categoryName);
}
