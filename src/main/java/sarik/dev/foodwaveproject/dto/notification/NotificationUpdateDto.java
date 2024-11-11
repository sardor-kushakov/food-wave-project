package sarik.dev.foodwaveproject.dto.notification;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationUpdateDto {

    @NotNull(message = "Notification ID is required") // Xabarnoma ID majburiy
    private Long notificationId;

    @NotNull(message = "IsRead status is required") // O'qilganligini belgilash majburiy
    private Boolean isRead;

    // Xabarnoma o'qilganligini belgilash uchun yordamchi metod
    public void markAsRead() {
        this.isRead = true;
    }
}
