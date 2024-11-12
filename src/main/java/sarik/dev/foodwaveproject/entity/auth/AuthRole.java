package sarik.dev.foodwaveproject.entity.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "auth_roles")
@NoArgsConstructor
@AllArgsConstructor
public class AuthRole {

    @Id
    private Long roleId;
    private String roleName;
}
