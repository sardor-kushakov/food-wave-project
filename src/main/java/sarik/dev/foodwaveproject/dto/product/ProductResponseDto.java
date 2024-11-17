package sarik.dev.foodwaveproject.dto.product;

import java.io.Serializable;

public record ProductResponseDto(
        Long id,
        String name,
        String description,
        Long price,
        Boolean available,
        Long categoryId,
        String categoryName) implements Serializable {
}
