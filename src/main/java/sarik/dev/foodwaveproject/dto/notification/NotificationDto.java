package sarik.dev.foodwaveproject.dto.notification;

import lombok.Getter;
import lombok.Setter;
import sarik.dev.foodwaveproject.enums.NotificationType;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationDto {

    private Long notificationId; // Xabarnoma ID

    private Long userId; // Xabarnoma tegishli foydalanuvchi ID

    private String message; // Xabarnoma matni

    private Boolean isRead; // Xabarnoma o'qilganligini belgilaydi

    private NotificationType type; // Xabarnoma turi (INFO, WARNING, ERROR)

    private LocalDateTime sentAt; // Xabarnoma yuborilgan vaqt
}
