package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sarik.dev.foodwaveproject.enums.OrderStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistory extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderHistoryId; // OrderHistory ID

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // Qaysi buyurtmaga tegishli ekanini bildiradi

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status; // Buyurtma holati (e.g., PENDING, COMPLETED, CANCELED)

    @Column(nullable = false)
    private LocalDateTime changeDate = LocalDateTime.now(); // Holat o'zgargan vaqt

    @Column(length = 200)
    private String comments; // Qo'shimcha izoh yoki sabab
}
