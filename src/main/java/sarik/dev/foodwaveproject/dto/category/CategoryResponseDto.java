package sarik.dev.foodwaveproject.dto.category;

import sarik.dev.foodwaveproject.dto.product.ProductResponseDto;

import java.io.Serializable;
import java.util.List;

public record CategoryResponseDto(
        Long id,
        String name,
        String description,
        boolean isActive,
        List<ProductResponseDto> products) implements Serializable {
}
