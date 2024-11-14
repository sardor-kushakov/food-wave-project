package sarik.dev.foodwaveproject.entity;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    @Size(min = 6, message = "Product description must contain at least 6 characters")
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

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<OrderItem> orderItems = new ArrayList<>();
}

