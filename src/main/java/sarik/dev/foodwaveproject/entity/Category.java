package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId; // Kategoriya ID raqami

    @Column(nullable = false, unique = true)
    private String categoryName; // Kategoriya nomi, majburiy va unikal

    @Column(nullable = false, length = 150)
    private String description; // Kategoriya haqida qisqacha tavsif

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>(); // Kategoriyaga tegishli mahsulotlar ro'yxati

    @Column(nullable = false)
    private Boolean isActive = true; // Kategoriya faol yoki faol emasligini ko'rsatadi
}
