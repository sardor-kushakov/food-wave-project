package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId; // Savatning ID raqami

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private AuthUser authUser; // Savatga tegishli foydalanuvchi

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>(); // Savatdagi barcha mahsulotlar ro'yxati

    // Umumiy narxni olish metodi
    @Transient
    private Long totalPrice = 0L; // Savatning jami narxi, bazada saqlanmaydi

    // Savatga yangi mahsulot qo'shish metodi
    public void addItem(CartItem item) {
        cartItems.add(item); // Mahsulotni savatga qo'shish
        item.setCart(this); // Mahsulotni ushbu savatga bog'lash
        calculateTotalPrice(); // Umumiy narxni qayta hisoblash
    }

    // Savatdan mahsulot olib tashlash metodi
    public void removeItem(CartItem item) {
        cartItems.remove(item); // Mahsulotni savatdan olib tashlash
        item.setCart(null); // Mahsulotni savat bilan bog'lanishini o'chirish
        calculateTotalPrice(); // Umumiy narxni qayta hisoblash
    }

    // Savatdagi jami narxni qayta hisoblash va o'rnatish metodi
    public void calculateTotalPrice() {
        this.totalPrice = cartItems.stream()
                .mapToLong(CartItem::calculateTotalPrice)
                .sum();
    }
}
