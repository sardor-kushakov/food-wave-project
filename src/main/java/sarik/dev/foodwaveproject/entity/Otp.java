package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;

import java.time.LocalDateTime;

@Entity
@Table(name = "otps")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long otpId; // OTP ID

    @Column(nullable = false, length = 6)
    private String code; // OTP kodi, masalan, 6 xonali raqam yoki harf raqam kombinatsiyasi

    @Column(nullable = false)
    private LocalDateTime expirationTime; // OTP amal qilish muddati

    @Column(nullable = false)
    private Boolean isUsed = false; // OTP ishlatilganligini bildiradi

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AuthUser authUser; // Ushbu OTP qaysi foydalanuvchi uchun ekanligini bildiradi

    @Column(nullable = false)
    private Integer attempts = 0; // OTP tekshirishga bo'lgan urinishlar soni

    @Column(nullable = false)
    private LocalDateTime createdTime = LocalDateTime.now(); // OTP yaratilgan vaqt

    // OTP amal qilish muddati tugaganligini tekshirish uchun yordamchi metod
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationTime);
    }

    // OTP ishlatilganligini yoki urinishlar limitiga yetganligini tekshirish
    public boolean isInvalid() {
        return isUsed || isExpired() || attempts >= 3; // Urinishlar limiti 3 ta
    }

    // Yangi urinishlarni hisoblash uchun yordamchi metod
    public void incrementAttempts() {
        this.attempts++;
    }
}
