package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;
import sarik.dev.foodwaveproject.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId; // Buyurtma ID

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AuthUser user; // Buyurtmani bergan foydalanuvchi

    @Column(nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now(); // Buyurtma berilgan vaqt

    @Column(nullable = false)
    private Long totalAmount; // Buyurtmaning umumiy summasi

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.PENDING; // Buyurtma holati

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>(); // Buyurtmadagi mahsulotlar ro'yxati

    // Buyurtmaga mahsulot qo'shish
    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
        item.setOrder(this);
        calculateTotalAmount();
    }

    // Buyurtmadan mahsulot olib tashlash
    public void removeOrderItem(OrderItem item) {
        orderItems.remove(item);
        item.setOrder(null);
        calculateTotalAmount();
    }

    // Umumiy summani hisoblash
    public void calculateTotalAmount() {
        totalAmount = orderItems.stream()
                .mapToLong(OrderItem::getTotalPrice)
                .sum();
    }

    // Buyurtmani tozalash
    public void clearOrderItems() {
        orderItems.forEach(item -> item.setOrder(null));
        orderItems.clear();
        calculateTotalAmount();
    }

    // Buyurtma yakunlanganligini tekshirish
    public boolean isCompleted() {
        return status == OrderStatus.COMPLETED;
    }

    // Buyurtmani bekor qilish
    public void cancelOrder() {
        this.status = OrderStatus.CANCELED;
        clearOrderItems();
    }

    // Buyurtma holatini yangilash
    public void updateOrderStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }
}
