package sarik.dev.foodwaveproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwaveproject.dto.cart.item.CartItemCreateDto;
import sarik.dev.foodwaveproject.dto.cart.item.CartItemDto;
import sarik.dev.foodwaveproject.dto.cart.item.CartItemResponseDto;
import sarik.dev.foodwaveproject.dto.cart.item.CartItemUpdateDto;
import sarik.dev.foodwaveproject.entity.Cart;
import sarik.dev.foodwaveproject.entity.CartItem;
import sarik.dev.foodwaveproject.entity.Product;
import sarik.dev.foodwaveproject.exception.ResourceNotFoundException;
import sarik.dev.foodwaveproject.mapper.CartItemMapper;
import sarik.dev.foodwaveproject.repository.CartItemRepository;
import sarik.dev.foodwaveproject.repository.CartRepository;
import sarik.dev.foodwaveproject.repository.ProductRepository;
import sarik.dev.foodwaveproject.service.CartItemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public CartItemResponseDto create(CartItemCreateDto createDto) {
        // Savatni tekshirish
        Cart cart = cartRepository.findById(createDto.cartId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "ID", createDto.cartId()));

        // Mahsulotni tekshirish
        Product product = productRepository.findById(createDto.productId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "ID", createDto.productId()));

        // Yangi savat elementi yaratish
        CartItem cartItem = cartItemMapper.fromCreateDto(createDto);
        cartItem.setCart(cart);
        cartItem.setProduct(product);

        CartItem savedCartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.toResponseDto(savedCartItem);
    }

    @Override
    public CartItemDto getById(Long id) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem", "ID", id));
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    public List<CartItemResponseDto> getByCartId(Long cartId) {
        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);
        if (cartItems.isEmpty()) {
            throw new ResourceNotFoundException("CartItem", "Cart ID", cartId);
        }
        return cartItems.stream()
                .map(cartItemMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CartItemResponseDto update(Long id, CartItemUpdateDto updateDto) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem", "ID", id));

        cartItemMapper.fromUpdateDto(updateDto, cartItem);

        CartItem updatedCartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.toResponseDto(updatedCartItem);
    }

    @Override
    public void delete(Long id) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem", "ID", id));
        cartItemRepository.delete(cartItem);
    }

    @Override
    public void deleteByCartId(Long cartId) {
        cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "ID", cartId));

        cartItemRepository.deleteByCartId(cartId);
    }

    @Override
    public void deleteByCartIdAndProductId(Long cartId, Long productId) {
        cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "ID", cartId));

        productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "ID", productId));

        cartItemRepository.deleteByCartIdAndProductId(cartId, productId);
    }
}
