package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwaveproject.entity.Notification;
import sarik.dev.foodwaveproject.enums.NotificationType;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Foydalanuvchi ID bo'yicha barcha xabarlarni olish
    List<Notification> findByUserId(Long userId);

    // Foydalanuvchi ID va o'qilgan holat bo'yicha xabarlarni olish
    List<Notification> findByUserIdAndIsRead(Long userId, boolean isRead);

    // Xabar turi bo'yicha qidirish
    List<Notification> findByType(NotificationType type);

    // O'qilgan holat bo'yicha xabarlarni olish
    List<Notification> findByIsRead(boolean isRead);

    // Foydalanuvchi ID va xabar turi bo'yicha qidirish
    List<Notification> findByUserIdAndType(Long userId, NotificationType type);
}
