package sarik.dev.foodwaveproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Long price;

    private String image;

    private String description;

    @ElementCollection
    @Column(nullable = false)
    private List<String> ingredients = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id" , nullable = false)
    private Category category;

    @Column(nullable = false)
    private boolean isPresent = false;

    @Column(nullable = false)
    private Long discount = 0L;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    private List<OrderItem> orderItems = new ArrayList<>();
}

