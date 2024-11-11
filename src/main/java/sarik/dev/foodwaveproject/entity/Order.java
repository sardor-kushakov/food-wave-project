package sarik.dev.foodwaveproject.entity;


import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime deliveredAt;
    private Status status;
    @ElementCollection
    @MapKeyJoinColumn(name = "food_id")
    @Column(name = "products")
    private Map<Product , Integer> products;
    private String location;
    private String userPhone;
    private String userEmail;
    private String userMessage;
    private Long price;
}
