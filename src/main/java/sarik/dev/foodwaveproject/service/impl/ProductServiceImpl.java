package sarik.dev.foodwaveproject.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sarik.dev.foodwaveproject.dto.categoryDto.CategoryResponseDTO;
import sarik.dev.foodwaveproject.dto.productDto.CreateProductDto;
import sarik.dev.foodwaveproject.entity.Category;
import sarik.dev.foodwaveproject.entity.Product;
import sarik.dev.foodwaveproject.exception.ResourceNotFoundException;
import sarik.dev.foodwaveproject.repository.CategoryRepository;
import sarik.dev.foodwaveproject.repository.ProductRepository;
import sarik.dev.foodwaveproject.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryServiceImpl categoryService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, CategoryServiceImpl categoryService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Product createProduct(Product product) {
        product.setPrice(product.getPrice()*100);
        product.setDiscount(product.getDiscount()*100);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            product.setPrice(product.getPrice()/100);
            product.setDiscount(product.getDiscount()/100);
        }
        return products;
    }

    @Override
    public Optional<Product> getProductById(int id) {//TODO int o'ylash kerak hali
        if (productRepository.existsById(id)) {
           Optional<Product> product = productRepository.findById(id);
           if (product.isPresent()) {
               product.get().setPrice(product.get().getPrice()/100);
               return Optional.of(product.get());
           }else throw new ResourceNotFoundException("Product not found");
        }else throw new ResourceNotFoundException("Product not found");
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

    @Override
    public List<Product> getProductsByCategoryName(String categoryName) {
        Optional<Category> category = categoryRepository.findByCategoryName(categoryName);
        if (category.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
        List<Product> products = productRepository.findByCategory(category.get());
        for (Product product : products) {
            product.setPrice(product.getPrice()/100);
            product.setDiscount(product.getDiscount()/100);
        }
        return products;
    }
}
