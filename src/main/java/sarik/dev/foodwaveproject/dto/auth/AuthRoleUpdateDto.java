package sarik.dev.foodwaveproject.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRoleUpdateDto {

    @NotBlank(message = "Role ID is required")
    private Integer roleId; // Role ID, bu maydon yangilash operatsiyasi uchun talab qilinadi

    @NotBlank(message = "Role name cannot be blank")
    @Size(min = 3, max = 50, message = "Role name must be between 3 and 50 characters")
    private String name; // Role nomi, yangilash uchun, uzunligi 3-50 belgidan iborat bo'lishi kerak

    @NotBlank(message = "Role description cannot be blank")
    @Size(max = 150, message = "Role description must not exceed 150 characters")
    private String description; // Role tavsifi, maksimal uzunlik 150 ta belgidan oshmasligi kerak
}
