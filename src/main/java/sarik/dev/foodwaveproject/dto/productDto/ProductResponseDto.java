package sarik.dev.foodwaveproject.dto.productDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sarik.dev.foodwaveproject.entity.Category;
import sarik.dev.foodwaveproject.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductResponseDto {

    private Long id;

    @NotBlank
    @Size(min = 3, message = "Product name must contain at least 3 characters")
    private String productName;

    @Positive
    private Long price;

    private String image;

    @NotBlank
    @Size(min = 6, message = "Product description must contain at least 6 characters")
    private String description;

    @NotEmpty
    private List<String> ingredients = new ArrayList<>();

    private Category category;

    private boolean isPresent;

    private Long discount = 0L;

    private List<OrderItem> orderItems = new ArrayList<>();
}
