package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ingredients") // Jadval nomi 'ingredients' qilib belgilandi
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredientId; // Ingredient ID

    @Column(nullable = false)
    private String name; // Ingredient nomi (masalan, "Un", "Shakar")

    @Column(nullable = false)
    private Double quantity; // Miqdor (masalan, 200.0)

    @Column(nullable = false, length = 20)
    private String unit; // O'lchov birligi (masalan, "g", "ml", "piece")
}
