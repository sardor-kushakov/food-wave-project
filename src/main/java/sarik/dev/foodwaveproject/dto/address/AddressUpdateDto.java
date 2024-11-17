package sarik.dev.foodwaveproject.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record AddressUpdateDto(
        @NotBlank(message = "Street cannot be blank")
        @Size(max = 100, message = "Street name cannot exceed 100 characters")
        String street,

        @NotBlank(message = "City cannot be blank")
        @Size(max = 50, message = "City name cannot exceed 50 characters")
        String city,

        @NotBlank(message = "District cannot be blank")
        @Size(max = 50, message = "District name cannot exceed 50 characters")
        String district,

        @NotBlank(message = "Country cannot be blank")
        @Size(max = 50, message = "Country name cannot exceed 50 characters")
        String country,

        @Size(max = 200, message = "Additional info cannot exceed 200 characters")
        String additionalInfo) implements Serializable {
}
