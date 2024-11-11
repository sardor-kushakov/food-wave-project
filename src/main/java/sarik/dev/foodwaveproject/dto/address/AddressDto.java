package sarik.dev.foodwaveproject.dto.address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

    private Long addressId; // Unique ID

    private String street; // Ko'cha nomi yoki uy manzili

    private String city; // Shahar nomi

    private String district; // Tuman nomi

    private String apartment; // Kvartira raqami

    private String country; // Mamlakat nomi

    private String additionalInfo; // Qo'shimcha ma'lumotlar

    // To'liq manzilni qaytarish
    public String getFullAddress() {
        StringBuilder fullAddress = new StringBuilder(street);

        if (apartment != null && !apartment.isEmpty()) {
            fullAddress.append(", Kvartira: ").append(apartment);
        }

        fullAddress.append(", ").append(district)
                .append(", ").append(city)
                .append(", ").append(country);

        if (additionalInfo != null && !additionalInfo.isEmpty()) {
            fullAddress.append(" (").append(additionalInfo).append(")");
        }

        return fullAddress.toString();
    }
}
