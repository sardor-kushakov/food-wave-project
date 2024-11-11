package sarik.dev.foodwaveproject.dto.notification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import sarik.dev.foodwaveproject.enums.NotificationType;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationCreateDto {

    @NotNull(message = "User ID is required") // Foydalanuvchi ID majburiy
    private Long userId;

    @NotBlank(message = "Message is required") // Xabarnoma matni majburiy
    private String message;

    @NotNull(message = "Notification type is required") // Xabarnoma turi majburiy
    private NotificationType type;

    @NotNull(message = "Sent date is required") // Xabarnoma yuborilgan vaqt majburiy
    private LocalDateTime sentAt;

}
