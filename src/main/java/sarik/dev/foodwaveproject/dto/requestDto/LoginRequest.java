package sarik.dev.foodwaveproject.dto.requestDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link sarik.dev.foodwaveproject.entity.auth.AuthUser}
 */

public record LoginRequest(
    @Email
    String email,
    @Column(nullable = false)
    @NotBlank
    String password) implements Serializable{
}