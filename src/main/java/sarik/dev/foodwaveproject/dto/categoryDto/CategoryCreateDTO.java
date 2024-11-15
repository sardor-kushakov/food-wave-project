package sarik.dev.foodwaveproject.dto.categoryDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateDTO {

    @NotBlank(message = "Category name cannot be blank")
    private String categoryName;
}
