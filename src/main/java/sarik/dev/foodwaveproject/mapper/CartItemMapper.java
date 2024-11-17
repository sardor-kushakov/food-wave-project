package sarik.dev.foodwaveproject.mapper;

import org.mapstruct.Mapper;
import sarik.dev.foodwaveproject.dto.cart.item.CartItemCreateDto;
import sarik.dev.foodwaveproject.dto.cart.item.CartItemDto;
import sarik.dev.foodwaveproject.dto.cart.item.CartItemResponseDto;
import sarik.dev.foodwaveproject.dto.cart.item.CartItemUpdateDto;
import sarik.dev.foodwaveproject.entity.CartItem;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    // CartItemCreateDto -> CartItem
    CartItem fromCreateDto(CartItemCreateDto dto);

    // CartItem -> CartItemDto
    CartItemDto toDto(CartItem cartItem);

    // CartItem -> CartItemResponseDto
    CartItemResponseDto toResponseDto(CartItem cartItem);

    // CartItemUpdateDto -> CartItem
    CartItem fromUpdateDto(CartItemUpdateDto dto, CartItem cartItem);
}
