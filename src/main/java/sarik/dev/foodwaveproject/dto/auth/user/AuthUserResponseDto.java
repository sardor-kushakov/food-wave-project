package sarik.dev.foodwaveproject.dto.auth.user;

import sarik.dev.foodwaveproject.dto.address.AddressDto;
import sarik.dev.foodwaveproject.dto.cart.CartDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

public record AuthUserResponseDto(
        Long id,
        String name,
        String email,
        boolean isVerified,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Set<String> roles,
        Set<AddressDto> addresses,
        CartDto cart) implements Serializable {
}
