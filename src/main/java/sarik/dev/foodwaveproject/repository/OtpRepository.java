package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwaveproject.entity.Otp;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {

    // Foydalanuvchi ID va ishlatilmagan OTP bo'yicha qidirish
    Optional<Otp> findByRecipientIdAndIsUsedFalse(Long userId);

    // Foydalanuvchi ID va kod bo'yicha qidirish
    Optional<Otp> findByRecipientIdAndCode(Long userId, String code);

    // Amal qilish muddati o'tgan barcha OTPlarni o'chirish
    void deleteByExpirationTimeBefore(LocalDateTime currentTime);
}
