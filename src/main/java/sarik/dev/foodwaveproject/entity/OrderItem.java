package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId; // Order item ID

    @Column(nullable = false)
    private Double quantity; // Mahsulot miqdori

    @Column(nullable = false)
    private Long unitPrice; // Mahsulotning bir dona narxi

    @Column(nullable = false)
    private Long totalPrice; // Mahsulotning jami narxi (quantity * unitPrice)

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // Ushbu mahsulot tegishli bo'lgan buyurtma

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Buyurtma qilingan mahsulot

    // Umumiy narxni hisoblaydigan yordamchi metod
    public void calculateTotalPrice() {
        this.totalPrice = unitPrice * quantity.longValue();
    }

    // Mahsulot miqdorini yangilash
    public void updateQuantity(Double newQuantity) {
        this.quantity = newQuantity;
        calculateTotalPrice();
    }

    // OrderItem to'g'riligini tekshirish
    public boolean isValid() {
        return quantity > 0 && unitPrice >= 0;
    }

    // Automatik totalPrice qayta hisoblash uchun setter metodlar
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
        calculateTotalPrice();
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
        calculateTotalPrice();
    }
}
