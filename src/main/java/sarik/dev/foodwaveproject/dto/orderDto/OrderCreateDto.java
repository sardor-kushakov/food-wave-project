package sarik.dev.foodwaveproject.dto.orderDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import sarik.dev.foodwaveproject.dto.cartItemDto.CartItemCreateDto;

import java.util.List;

@Data
public class OrderCreateDto {
    @Email
    private String email;

    @NotEmpty
    private List<CartItemCreateDto> orderItems;
}
