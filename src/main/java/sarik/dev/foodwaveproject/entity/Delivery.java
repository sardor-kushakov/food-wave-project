package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;
import sarik.dev.foodwaveproject.enums.DeliveryStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Delivery extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId; // Delivery ID

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // Qaysi buyurtmaga yetkazish amalga oshirilayotganini bildiradi

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address deliveryAddress; // Yetkazib berish manzili

    @Column(nullable = false)
    private LocalDateTime deliveryDate; // Yetkazib berilishi rejalashtirilgan vaqt

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status = DeliveryStatus.PENDING; // Yetkazib berish holati

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id")
    private AuthUser courier; // Yetkazuvchi sifatida ishlaydigan AuthUser
}
