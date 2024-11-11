package sarik.dev.foodwaveproject.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressCreateDto {

    @NotBlank(message = "Street is required") // Ko'cha nomi bo'sh bo'lmasligi kerak
    @Size(max = 100, message = "Street can be up to 100 characters")
    private String street;

    @NotBlank(message = "City is required") // Shahar nomi bo'sh bo'lmasligi kerak
    @Size(max = 50, message = "City can be up to 50 characters")
    private String city;

    @NotBlank(message = "District is required") // Tuman nomi bo'sh bo'lmasligi kerak
    @Size(max = 50, message = "District can be up to 50 characters")
    private String district;

    @Size(max = 20, message = "Apartment can be up to 20 characters") // Kvartira raqami ixtiyoriy, uzunligi 20 belgigacha
    private String apartment;

    @NotBlank(message = "Country is required") // Mamlakat nomi bo'sh bo'lmasligi kerak
    @Size(max = 50, message = "Country can be up to 50 characters")
    private String country;

    @Size(max = 150, message = "Additional info can be up to 150 characters") // Qo'shimcha ma'lumotlar ixtiyoriy
    private String additionalInfo;
}
