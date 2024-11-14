package sarik.dev.foodwaveproject.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sarik.dev.foodwaveproject.dto.productDto.CreateProductDto;
import sarik.dev.foodwaveproject.dto.productDto.ProductResponseDto;
import sarik.dev.foodwaveproject.entity.Product;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    Product toProduct(CreateProductDto dto);

    @Mapping(target = "isPresent", source = "present")
    ProductResponseDto toProductResponseDto(Product product);
    List<ProductResponseDto> toProductResponseDtoList(List<Product> products);
}
