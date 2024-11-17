package sarik.dev.foodwaveproject.mapper;

import org.mapstruct.Mapper;
import sarik.dev.foodwaveproject.dto.product.ProductCreateDto;
import sarik.dev.foodwaveproject.dto.product.ProductDto;
import sarik.dev.foodwaveproject.dto.product.ProductResponseDto;
import sarik.dev.foodwaveproject.dto.product.ProductUpdateDto;
import sarik.dev.foodwaveproject.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // ProductCreateDto -> Product
    Product fromCreateDto(ProductCreateDto dto);

    // Product -> ProductDto
    ProductDto toDto(Product product);

    // Product -> ProductResponseDto
    ProductResponseDto toResponseDto(Product product);

    // ProductUpdateDto -> Product
    Product fromUpdateDto(ProductUpdateDto dto, Product product);
}
