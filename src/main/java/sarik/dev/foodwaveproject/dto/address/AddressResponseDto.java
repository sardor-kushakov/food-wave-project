package sarik.dev.foodwaveproject.dto.address;

import java.io.Serializable;

public record AddressResponseDto(
        Long id,
        String street,
        String city,
        String district,
        String country,
        String additionalInfo) implements Serializable {
}
