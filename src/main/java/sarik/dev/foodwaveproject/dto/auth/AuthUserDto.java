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
public class AuthUserDto {

    @NotNull(message = "User ID cannot be null") // Foydalanuvchi IDsi bo'lishi shart
    private Long userId;

    @NotBlank(message = "Full name is required") // Foydalanuvchining to'liq ismi bo'sh bo'lmasligi kerak
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private String fullName;

    @Email(message = "Email should be valid") // Email formatida bo'lishi shart
    @NotBlank(message = "Email is required") // Email bo'sh bo'lmasligi kerak
    private String email;

    @NotNull(message = "Verification status is required") // Tasdiqlash holati null bo'lmasligi kerak
    private Boolean isVerified;

    @NotNull(message = "Roles cannot be null") // Rollar bo'sh bo'lmasligi kerak
    private Set<@NotBlank(message = "Role name cannot be blank") String> roleNames;

    @NotNull(message = "Addresses cannot be null") // Manzillar ro'yxati null bo'lmasligi kerak
    private List<@NotBlank(message = "Address cannot be blank") String> addresses;
}
