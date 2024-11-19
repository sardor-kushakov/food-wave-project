package sarik.dev.foodwaveproject.entity.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "auth_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRole {

    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String roleName;
}
