package sarik.dev.foodwaveproject.dto.auth.role;

import java.io.Serializable;

public record AuthRoleResponseDto(
        Integer id,
        String name,
        String description) implements Serializable {
}
