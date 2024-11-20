package sarik.dev.foodwaveproject.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sarik.dev.foodwaveproject.configuration.SessionUser;
import sarik.dev.foodwaveproject.dto.cartDto.CartCreateDto;
import sarik.dev.foodwaveproject.dto.cartDto.CartResponseDto;
import sarik.dev.foodwaveproject.dto.cartDto.CartUpdateDto;
import sarik.dev.foodwaveproject.dto.cartItemDto.CartItemCreateDto;
import sarik.dev.foodwaveproject.dto.cartItemDto.CartItemResponseDto;
import sarik.dev.foodwaveproject.dto.cartItemDto.CartItemUpdateDto;
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
    private final SessionUser sessionUser;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, AuthUserRepository authUserRepository, SessionUser sessionUser) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.authUserRepository = authUserRepository;
        this.sessionUser = sessionUser;
    }

    @Transactional
    @Override
    public CartResponseDto createCart(CartCreateDto cartCreateDto) {
        AuthUser user = authUserRepository.findById(sessionUser.getCurrentUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Cart cart = new Cart();
        cart.setAuthUser(user);

        // CartItem ob'ektlarini o'rnatish va bog'lash
        List<CartItem> cartItems = cartCreateDto.getCartItems().stream()
                .map(dto -> {
                    CartItem cartItem = toCartItem(dto);
                    cartItem.setCart(cart); // `Cart` ni `CartItem` bilan bog'lash
                    return cartItem;
                }).collect(Collectors.toList());
        cart.setCartItems(cartItems);

        // Umumiy narxni hisoblash
        cart.setTotalPrice(calculateTotalPrice(cartItems));

        cartRepository.save(cart);
        return toCartResponseDto(cart);
    }

    @Transactional
    @Override
    public CartResponseDto updateCart(CartUpdateDto cartUpdateDto) {
        Cart cart = cartRepository.findById(cartUpdateDto.getCartId())
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        for (CartItemUpdateDto updateDto : cartUpdateDto.getCartItems()) {
            Optional<CartItem> cartItemOpt = cart.getCartItems().stream()
                    .filter(item -> item.getCartItemId().equals(updateDto.getCartItemId()))
                    .findFirst();

            if (cartItemOpt.isPresent()) {
                CartItem cartItem = cartItemOpt.get();
                cartItem.setQuantity(updateDto.getQuantity());
                cartItem.setProductPrice(cartItem.getProduct().getPrice() - cartItem.getDiscount());
            }
        }

        cart.setTotalPrice(calculateTotalPrice(cart.getCartItems()));
        cartRepository.save(cart);

        return toCartResponseDto(cart);
    }

//    @Override
//    public CartResponseDto getCartByUserId(Long userId) {
//        Cart cart = cartRepository.findUniqueCartByAuthUserId(userId)
//                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
//        return toCartResponseDto(cart);
//    }

    @Override
    public List<CartResponseDto> getCartByUserId(Long userId) {
        List<Cart> carts = cartRepository.findAllByAuthUserId(userId);

        if (carts.isEmpty()) {
            throw new IllegalArgumentException("No carts found for user");
        }

        return carts.stream()
                .map(this::toCartResponseDto)
                .collect(Collectors.toList());
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
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (!product.isPresent()) {
            throw new IllegalArgumentException("Product is not available: " + dto.getProductId());
        }

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(dto.getQuantity());
        cartItem.setDiscount(product.getDiscount());
        cartItem.setProductPrice(product.getPrice() - cartItem.getDiscount());
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
        long totalPriceSom = cart.getTotalPrice();
        return new CartResponseDto(cart.getCartId(), cart.getAuthUser().getId(), cartItems, totalPriceSom);
    }
}