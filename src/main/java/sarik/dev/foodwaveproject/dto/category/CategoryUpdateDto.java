package sarik.dev.foodwaveproject.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryUpdateDto {

    @NotNull(message = "Category ID is required") // Kategoriya ID majburiy
    private Long categoryId;

    @NotBlank(message = "Category name is required") // Kategoriya nomi majburiy
    private String categoryName;

    @NotBlank(message = "Description is required") // Tavsif majburiy
    private String description;

    @NotNull(message = "isActive status is required") // Faollik statusi majburiy
    private Boolean isActive;
}
