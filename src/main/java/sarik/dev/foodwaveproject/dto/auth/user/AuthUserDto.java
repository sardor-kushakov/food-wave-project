package sarik.dev.foodwaveproject.dto.auth.user;

import sarik.dev.foodwaveproject.dto.address.AddressDto;
import sarik.dev.foodwaveproject.dto.auth.role.AuthRoleDto;
import sarik.dev.foodwaveproject.dto.cart.CartDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record AuthUserDto(
        Long id,
        String name,
        String email,
        boolean isVerified,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Set<AuthRoleDto> roles,
        List<AddressDto> addresses,
        CartDto cart) implements Serializable {
}
