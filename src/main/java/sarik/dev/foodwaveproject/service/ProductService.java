package sarik.dev.foodwaveproject.service;

import sarik.dev.foodwaveproject.dto.productDto.CreateProductDto;
import sarik.dev.foodwaveproject.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Optional<Product> getProductById(int id);
    Product updateProduct(CreateProductDto dto, Product product);
    void deleteProductById(int id);
}
