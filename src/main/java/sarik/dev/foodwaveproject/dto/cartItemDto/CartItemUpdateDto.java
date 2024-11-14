package sarik.dev.foodwaveproject.dto.cartItemDto;
import lombok.Data;

@Data
public class CartItemUpdateDto {
    private Long cartItemId; // Savatchadagi mahsulot ID-si
    private Integer quantity; // Yangilangan miqdor
    private long discountSom; // Yangilangan chegirma, so'mda kiritiladi
}