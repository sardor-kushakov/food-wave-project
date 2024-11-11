package sarik.dev.foodwaveproject.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class AuthUserUpdateDTO {

    @NotBlank(message = "Full name must not be blank") // To'liq ism majburiy
    private String fullName;

    @NotBlank(message = "Email must not be blank") // Email majburiy
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Verification status must be specified") // Tasdiqlash statusi
    private Boolean isVerified;

    @NotNull(message = "Roles must be specified") // Rollar majburiy
    private Set<Long> roleIds; // Rollar ID-lari
    
    private List<Long> addressIds; // Manzil ID-lari, ixtiyoriy
}
