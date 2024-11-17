package sarik.dev.foodwaveproject.dto.notification;

import java.io.Serializable;

public record NotificationUpdateDto(
        boolean isRead) implements Serializable {
}
