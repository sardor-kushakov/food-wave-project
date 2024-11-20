package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDeleteAll;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemHistoryId;

    private Long productId;
    private Integer quantity;
    private Long discount;
    private Long orderedProductPrice;
}
