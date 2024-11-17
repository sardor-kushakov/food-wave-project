package sarik.dev.foodwaveproject.dto.order.item;

import sarik.dev.foodwaveproject.dto.product.ProductDto;

import java.io.Serializable;

public record OrderItemDto(
        Long id,
        Double quantity,
        Long unitPrice,
        Long totalPrice,
        ProductDto product) implements Serializable {
}
