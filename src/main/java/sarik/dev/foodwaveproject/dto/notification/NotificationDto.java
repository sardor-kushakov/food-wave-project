package sarik.dev.foodwaveproject.dto.notification;

import sarik.dev.foodwaveproject.dto.auth.user.AuthUserDto;
import sarik.dev.foodwaveproject.enums.NotificationType;

import java.io.Serializable;
import java.time.LocalDateTime;

public record NotificationDto(
        Long id,
        String message,
        boolean isRead,
        NotificationType type,
        LocalDateTime sentAt,
        AuthUserDto user) implements Serializable {
}
