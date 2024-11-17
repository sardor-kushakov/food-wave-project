package sarik.dev.foodwaveproject.dto.category;

import sarik.dev.foodwaveproject.dto.product.ProductDto;

import java.io.Serializable;
import java.util.List;

public record CategoryDto(
        Long id,
        String name,
        String description,
        boolean isActive,
        List<ProductDto> products) implements Serializable {
}
