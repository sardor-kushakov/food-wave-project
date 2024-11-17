package sarik.dev.foodwaveproject.dto.order.item;

import java.io.Serializable;

public record OrderItemResponseDto(
        Long id,
        Long productId,
        String productName,
        Double quantity,
        Long unitPrice,
        Long totalPrice) implements Serializable {
}
