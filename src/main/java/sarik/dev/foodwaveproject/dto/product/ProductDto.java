package sarik.dev.foodwaveproject.dto.product;

import sarik.dev.foodwaveproject.dto.category.CategoryDto;

import java.io.Serializable;

public record ProductDto(
        Long id,
        String name,
        String description,
        Long price,
        boolean available,
        CategoryDto category) implements Serializable {
}
