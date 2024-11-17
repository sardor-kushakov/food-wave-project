package sarik.dev.foodwaveproject.dto.notification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sarik.dev.foodwaveproject.enums.NotificationType;

import java.io.Serializable;

public record NotificationCreateDto(
        @NotBlank(message = "Message cannot be blank")
        String message,

        @NotNull(message = "Notification type cannot be null")
        NotificationType type,

        @NotNull(message = "User ID cannot be null")
        Long userId) implements Serializable {
}
