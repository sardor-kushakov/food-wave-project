package sarik.dev.foodwaveproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwaveproject.dto.cart.CartCreateDto;
import sarik.dev.foodwaveproject.dto.cart.CartDto;
import sarik.dev.foodwaveproject.dto.cart.CartResponseDto;
import sarik.dev.foodwaveproject.dto.cart.CartUpdateDto;
import sarik.dev.foodwaveproject.entity.Cart;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;
import sarik.dev.foodwaveproject.exception.ResourceNotFoundException;
import sarik.dev.foodwaveproject.mapper.CartMapper;
import sarik.dev.foodwaveproject.repository.AuthUserRepository;
import sarik.dev.foodwaveproject.repository.CartRepository;
import sarik.dev.foodwaveproject.service.CartService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final AuthUserRepository authUserRepository;
    private final CartMapper cartMapper;

    @Override
    public CartResponseDto create(CartCreateDto createDto) {
        // Foydalanuvchini tekshirish
        AuthUser user = authUserRepository.findById(createDto.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", createDto.userId()));

        // Savatni yaratish
        Cart cart = cartMapper.fromCreateDto(createDto);
        cart.setUser(user);

        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toResponseDto(savedCart);
    }

    @Override
    public CartDto getById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "ID", id));
        return cartMapper.toDto(cart);
    }

    @Override
    public CartResponseDto getByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "User ID", userId));
        return cartMapper.toResponseDto(cart);
    }

    @Override
    public List<CartResponseDto> getAll() {
        List<Cart> carts = cartRepository.findAll();
        return carts.stream()
                .map(cartMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CartResponseDto update(Long id, CartUpdateDto updateDto) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "ID", id));

        cartMapper.fromUpdateDto(updateDto, cart);

        Cart updatedCart = cartRepository.save(cart);
        return cartMapper.toResponseDto(updatedCart);
    }

    @Override
    public void delete(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "ID", id));
        cartRepository.delete(cart);
    }
}
