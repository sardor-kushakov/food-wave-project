package sarik.dev.foodwaveproject.service.impl;

import org.springframework.stereotype.Service;
import sarik.dev.foodwaveproject.dto.productDto.CreateProductDto;
import sarik.dev.foodwaveproject.entity.Category;
import sarik.dev.foodwaveproject.entity.Product;
import sarik.dev.foodwaveproject.repository.CategoryRepository;
import sarik.dev.foodwaveproject.repository.ProductRepository;
import sarik.dev.foodwaveproject.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(CreateProductDto dto, Product product) {
        Optional<Category> category = categoryRepository.findByCategoryName(dto.getCategory().getName());
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        product.setImage(dto.getImage());
        product.setDescription(dto.getDescription());
        product.setIngredients(dto.getIngredients());
        product.setCategory(category.get());
        return productRepository.save(product);
    }


    @Override
    public void deleteProductById(int id) {
       productRepository.deleteById(id);
    }
}
