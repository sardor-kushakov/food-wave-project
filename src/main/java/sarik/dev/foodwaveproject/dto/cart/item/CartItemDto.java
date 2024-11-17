package sarik.dev.foodwaveproject.dto.cart.item;

import sarik.dev.foodwaveproject.dto.product.ProductDto;

import java.io.Serializable;

public record CartItemDto(
        Long id,
        ProductDto product,
        Integer quantity,
        Integer discountPercentage,
        Long productPrice) implements Serializable {
}
