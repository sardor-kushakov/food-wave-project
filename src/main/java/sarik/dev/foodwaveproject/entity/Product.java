package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId; // Mahsulot ID

    @Column(nullable = false)
    private String name; // Mahsulot nomi (masalan, "Shokolad", "Un")

    @Column(nullable = true, length = 150)
    private String description; // Mahsulot tavsifi (masalan, mahsulotning o'ziga xos xususiyatlari)

    @Column(nullable = false)
    private Long price; // Mahsulot narxi (masalan, 12.99)

    @Column(nullable = false)
    private Boolean available = true; // Mahsulot mavjudligi, true - mavjud, false - mavjud emas

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category; // Mahsulot kategoriyasi

    // Mavjudlik holatini o'zgartirish
    public void toggleAvailability() {
        this.available = !this.available;
    }

    // Mahsulotga chegirma qo'llash
    public void applyDiscount(Double percentage) {
        if (percentage > 0 && percentage <= 100) {
            this.price = Math.round(this.price * (1 - percentage / 100.0)); // 100.0 ga bo'linishi bilan natija to'g'ri chiqadi
        }
    }
}
