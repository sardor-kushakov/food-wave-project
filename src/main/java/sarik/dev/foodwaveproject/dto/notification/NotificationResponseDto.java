package sarik.dev.foodwaveproject.dto.notification;

import sarik.dev.foodwaveproject.enums.NotificationType;

import java.io.Serializable;
import java.time.LocalDateTime;

public record NotificationResponseDto(
        Long id,
        String message,
        boolean isRead,
        NotificationType type,
        LocalDateTime sentAt,
        Long userId) implements Serializable {
}
