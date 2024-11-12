package sarik.dev.foodwaveproject.dto.productDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sarik.dev.foodwaveproject.dto.CategoryDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CreateProductDto {
    private String productName;
    private double price;
    private String image;
    private String description;
    private List<String> ingredients = new ArrayList<>();
    private CategoryDto category;
}
