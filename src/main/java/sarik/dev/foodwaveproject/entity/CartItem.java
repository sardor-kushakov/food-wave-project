package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId; // Savatdagi mahsulotning ID raqami

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart; // Mahsulot tegishli bo'lgan savat

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Mahsulot ma'lumotlari

    @Column(nullable = false)
    private Integer quantity; // Mahsulot miqdori

    @Column(nullable = false)
    private Integer discountPercentage = 0; // Mahsulot uchun chegirma foizda

    @Column(nullable = false)
    private Long productPrice; // Mahsulotning yakka narxi

    // Mahsulotning jami narxini hisoblash
    public Long calculateTotalPrice() {
        return (productPrice * quantity) * (100 - discountPercentage) / 100;
    }
}
