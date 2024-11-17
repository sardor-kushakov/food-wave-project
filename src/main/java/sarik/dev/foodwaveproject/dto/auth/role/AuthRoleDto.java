package sarik.dev.foodwaveproject.dto.auth.role;

import java.io.Serializable;
import java.time.LocalDateTime;

public record AuthRoleDto(
        Integer id,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) implements Serializable {
}
