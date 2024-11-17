package sarik.dev.foodwaveproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import sarik.dev.foodwaveproject.dto.address.AddressCreateDto;
import sarik.dev.foodwaveproject.dto.address.AddressDto;
import sarik.dev.foodwaveproject.dto.address.AddressResponseDto;
import sarik.dev.foodwaveproject.dto.address.AddressUpdateDto;
import sarik.dev.foodwaveproject.entity.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    // AddressCreateDto -> Address
    @Mapping(target = "id", ignore = true)
    Address fromCreateDto(AddressCreateDto dto);

    // Address -> AddressDto
    AddressDto toDto(Address address);

    // Address -> AddressResponseDto
    AddressResponseDto toResponseDto(Address address);

    // AddressUpdateDto -> Address
    @Mapping(target = "id", ignore = true)
    Address fromUpdateDto(AddressUpdateDto dto, @MappingTarget Address address);
}
