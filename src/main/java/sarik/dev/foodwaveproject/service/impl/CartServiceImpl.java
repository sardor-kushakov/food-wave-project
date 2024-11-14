package sarik.dev.foodwaveproject.service.impl;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import sarik.dev.foodwaveproject.dto.cartDto.CartCreateDto;
import sarik.dev.foodwaveproject.dto.cartDto.CartResponseDto;
import sarik.dev.foodwaveproject.dto.cartDto.CartUpdateDto;
import sarik.dev.foodwaveproject.dto.cartItemDto.CartItemCreateDto;
import sarik.dev.foodwaveproject.dto.cartItemDto.CartItemUpdateDto;
import sarik.dev.foodwaveproject.dto.cartItemDto.CartItemResponseDto;
import sarik.dev.foodwaveproject.entity.Cart;
import sarik.dev.foodwaveproject.entity.CartItem;
import sarik.dev.foodwaveproject.entity.Product;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;
import sarik.dev.foodwaveproject.repository.AuthUserRepository;
import sarik.dev.foodwaveproject.repository.CartRepository;
import sarik.dev.foodwaveproject.repository.ProductRepository;
import sarik.dev.foodwaveproject.service.CartService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final AuthUserRepository authUserRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, AuthUserRepository authUserRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.authUserRepository = authUserRepository;
    }

    @Transactional
    @Override
    public CartResponseDto createCart(@Valid CartCreateDto cartCreateDto) {
        AuthUser user = authUserRepository.findById(1l)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        // TODO bu yerda session id kirib keladi session tayyor bo'lganda qoshaman

        Cart cart = new Cart();
        cart.setAuthUser(user);
        cart.setCartItems(cartCreateDto.getCartItems().stream().map(this::toCartItem).collect(Collectors.toList()));
        cart.setTotalPrice(calculateTotalPrice(cart.getCartItems())); // Tiyinda umumiy narx hisoblanadi

        cartRepository.save(cart);
        return toCartResponseDto(cart);
    }

    @Transactional
    @Override
    public CartResponseDto updateCart(@Valid CartUpdateDto cartUpdateDto) {
        Cart cart = cartRepository.findById(cartUpdateDto.getCartId())
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        for (CartItemUpdateDto updateDto : cartUpdateDto.getCartItems()) {
            Optional<CartItem> cartItemOpt = cart.getCartItems().stream()
                    .filter(item -> item.getCartItemId().equals(updateDto.getCartItemId()))
                    .findFirst();

            if (cartItemOpt.isPresent()) {
                CartItem cartItem = cartItemOpt.get();
                cartItem.setQuantity(updateDto.getQuantity()); // Yangilangan miqdor
                cartItem.setDiscount(updateDto.getDiscountSom() * 100); // So'mdan tiyinga o'zgartirish
                cartItem.setProductPrice(cartItem.getProduct().getPrice() - cartItem.getDiscount()); // Narxni yangilash
            }
        }

        cart.setTotalPrice(calculateTotalPrice(cart.getCartItems())); // Umumiy narxni qayta hisoblash
        cartRepository.save(cart);

        return toCartResponseDto(cart); // Yangilangan savatchani qaytarish
    }

    @Override
    public CartResponseDto getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByAuthUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        return toCartResponseDto(cart);
    }

    @Override
    public void deleteCartById(Long cartId) {
        if (cartRepository.existsById(cartId)) {
            cartRepository.deleteById(cartId);
        } else {
            throw new IllegalArgumentException("Cart not found");
        }
    }

    private CartItem toCartItem(CartItemCreateDto dto) {
        Product product = productRepository.findById(Math.toIntExact(dto.getProductId()))
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(dto.getQuantity());
        cartItem.setDiscount(product.getDiscount());
        cartItem.setProductPrice(product.getPrice() - cartItem.getDiscount()); // Narxni tiyinda hisoblash
        return cartItem;
    }

    private long calculateTotalPrice(List<CartItem> cartItems) {
        return cartItems.stream()
                .mapToLong(item -> (item.getProductPrice() * item.getQuantity()))
                .sum();
    }

    private CartResponseDto toCartResponseDto(Cart cart) {
        List<CartItemResponseDto> cartItems = cart.getCartItems().stream()
                .map(CartItemResponseDto::new)
                .collect(Collectors.toList());
        long totalPriceSom = cart.getTotalPrice() / 100; // Umumiy narxni so'mga aylantirish
        return new CartResponseDto(cart.getCartId(), cart.getAuthUser().getId(), cartItems, totalPriceSom);
    }
}