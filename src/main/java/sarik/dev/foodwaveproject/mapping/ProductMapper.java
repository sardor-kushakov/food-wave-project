package sarik.dev.foodwaveproject.mapping;

import org.mapstruct.Mapper;
import sarik.dev.foodwaveproject.dto.productDto.CreateProductDto;
import sarik.dev.foodwaveproject.entity.Product;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    Product toProduct(CreateProductDto dto);
}
