package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;
import sarik.dev.foodwaveproject.enums.NotificationType;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
public class Notification extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private boolean isRead = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    @Column(nullable = false)
    private LocalDateTime sentAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AuthUser user;
}
