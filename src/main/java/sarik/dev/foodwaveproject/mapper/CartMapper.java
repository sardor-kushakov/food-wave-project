package sarik.dev.foodwaveproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import sarik.dev.foodwaveproject.dto.cart.CartCreateDto;
import sarik.dev.foodwaveproject.dto.cart.CartDto;
import sarik.dev.foodwaveproject.dto.cart.CartResponseDto;
import sarik.dev.foodwaveproject.dto.cart.CartUpdateDto;
import sarik.dev.foodwaveproject.entity.Cart;

@Mapper(componentModel = "spring")
public interface CartMapper {

    // CartCreateDto -> Cart
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    Cart fromCreateDto(CartCreateDto dto);

    // Cart -> CartDto
    CartDto toDto(Cart cart);

    // Cart -> CartResponseDto
    @Mapping(target = "userId", source = "user.id")
    CartResponseDto toResponseDto(Cart cart);

    // CartUpdateDto -> Cart
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    Cart fromUpdateDto(CartUpdateDto dto, @MappingTarget Cart cart);
}
