package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.*;
import lombok.*;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;

}
