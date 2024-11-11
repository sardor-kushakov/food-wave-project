package sarik.dev.foodwaveproject.entity.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sarik.dev.foodwaveproject.entity.Address;
import sarik.dev.foodwaveproject.entity.Auditable;
import sarik.dev.foodwaveproject.entity.Cart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "auth_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String fullName; // to'liq ismi

    @Column(unique = true, nullable = false)
    private String email; // email manzili

    @Column(nullable = false)
    private String password; // paroli

    @Column(nullable = false)
    private Boolean isVerified = false; // email tasdiqlangan yoki yo'qligini ko'rsatadi

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<AuthRole> roles = new HashSet<>(); // rollari

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // `addresses` jadvali uchun `user_id` ustuni orqali ulanish
    private List<Address> addresses = new ArrayList<>(); // manzillari

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "cart_id") // `cart` uchun aniqlik kiritish
    private Cart cart; // foydalanuvchining savat obyekti
}
