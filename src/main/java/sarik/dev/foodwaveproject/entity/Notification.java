package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;
import sarik.dev.foodwaveproject.enums.NotificationType;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId; // Notification ID

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AuthUser user; // Xabarnoma tegishli foydalanuvchi

    @Column(nullable = false)
    private String message; // Xabarnoma matni

    @Column(nullable = false)
    private Boolean isRead = false; // Xabarnoma o'qilganligini belgilaydi

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type; // Xabarnoma turi (e.g., INFO, WARNING, ERROR)

    @Column(nullable = false)
    private LocalDateTime sentAt = LocalDateTime.now(); // Xabarnoma yuborilgan vaqt

    // Xabarnoma o'qilganligini belgilash uchun yordamchi metod
    public void markAsRead() {
        this.isRead = true;
    }

    // Xabarnomani o'qilmagan qilib belgilash uchun yordamchi metod
    public void markAsUnread() {
        this.isRead = false;
    }

    // Yaqinda yuborilganligini tekshirish
    public boolean isRecent() {
        return sentAt.isAfter(LocalDateTime.now().minusDays(1));
    }

    // Xabarnomaning oldin yuborilganligini tekshirish
    public boolean wasSentBefore(LocalDateTime dateTime) {
        return sentAt.isBefore(dateTime);
    }
}
