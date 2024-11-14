package sarik.dev.foodwaveproject.dto.productDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sarik.dev.foodwaveproject.dto.CategoryDto;
import sarik.dev.foodwaveproject.entity.Category;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UpdateProductDto {
    private String productName;
    private Long price;
    private String image;
    private String description;
    private List<String> ingredients = new ArrayList<>();
    private Category category;
}
