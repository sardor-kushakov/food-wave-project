package sarik.dev.foodwaveproject.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sarik.dev.foodwaveproject.dto.cartDto.CartCreateDto;
import sarik.dev.foodwaveproject.dto.cartDto.CartResponseDto;
import sarik.dev.foodwaveproject.dto.cartDto.CartUpdateDto;
import sarik.dev.foodwaveproject.service.CartService;

import java.util.List;


@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartResponseDto> createCart(@Valid @RequestBody CartCreateDto cartCreateDto) {
        CartResponseDto cart = cartService.createCart(cartCreateDto);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<CartResponseDto> updateCart(@Valid @RequestBody CartUpdateDto cartUpdateDto) {
        CartResponseDto cart = cartService.updateCart(cartUpdateDto);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/update-my-cart")
    ResponseEntity<CartResponseDto> updateMyCart(@Valid @RequestBody CartUpdateDto cartUpdateDto) {
        CartResponseDto cart = cartService.updateMyCart(cartUpdateDto);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartResponseDto>> getCartByUserId(@PathVariable Long userId) {
        List<CartResponseDto> carts = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/my-carts")
    public ResponseEntity<List<CartResponseDto>> createMyCarts() {
        List<CartResponseDto> carts = cartService.getMyCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }


    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCartById(@PathVariable Long cartId) {
        cartService.deleteCartById(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete-my-cart/{cartId}")
    public ResponseEntity<Void> deleteMyCart(@PathVariable Long cartId) {
        cartService.deleteMyCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
