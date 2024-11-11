package sarik.dev.foodwaveproject.dto.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

    private Long categoryId; // Kategoriya ID

    private String categoryName; // Kategoriya nomi

    private String description; // Kategoriya tavsifi

    private Boolean isActive; // Kategoriya faol yoki faol emasligi
}
