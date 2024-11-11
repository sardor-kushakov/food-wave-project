package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sarik.dev.foodwaveproject.enums.PaymentMethod;
import sarik.dev.foodwaveproject.enums.PaymentStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId; // Payment ID

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // Ushbu to'lov qaysi buyurtmaga tegishli

    @Column(nullable = false)
    private Long amount; // To'lov miqdori

    @Column(nullable = false)
    private LocalDateTime paymentDate = LocalDateTime.now(); // To'lov vaqti

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING; // To'lov holati (masalan, PENDING, COMPLETED, FAILED)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod; // To'lov turi (masalan, CREDIT_CARD, CASH, PAYPAL)

    // To'lov tugaganligini tekshirish uchun yordamchi metod
    public boolean isCompleted() {
        return this.status == PaymentStatus.COMPLETED;
    }

    // To'lovni "COMPLETED" sifatida belgilash uchun yordamchi metod
    public void markAsCompleted() {
        this.status = PaymentStatus.COMPLETED;
    }

    // To'lovni "FAILED" sifatida belgilash uchun yordamchi metod
    public void markAsFailed() {
        this.status = PaymentStatus.FAILED;
    }

    // To'lov holati PENDINGligini tekshirish
    public boolean isPending() {
        return this.status == PaymentStatus.PENDING;
    }
}
