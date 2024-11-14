package sarik.dev.foodwaveproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sarik.dev.foodwaveproject.dto.cartDto.CartCreateDto;
import sarik.dev.foodwaveproject.dto.cartDto.CartResponseDto;
import sarik.dev.foodwaveproject.dto.cartDto.CartUpdateDto;
import sarik.dev.foodwaveproject.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartResponseDto> createCart(@RequestBody CartCreateDto cartCreateDto) {
        CartResponseDto cart = cartService.createCart(cartCreateDto);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CartResponseDto> updateCart(@RequestBody CartUpdateDto cartUpdateDto) {
        CartResponseDto cart = cartService.updateCart(cartUpdateDto);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponseDto> getCartByUserId(@PathVariable Long userId) {
        CartResponseDto cart = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCartById(@PathVariable Long cartId) {
        cartService.deleteCartById(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
