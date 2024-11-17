package sarik.dev.foodwaveproject.service;

import sarik.dev.foodwaveproject.dto.address.AddressCreateDto;
import sarik.dev.foodwaveproject.dto.address.AddressDto;
import sarik.dev.foodwaveproject.dto.address.AddressResponseDto;
import sarik.dev.foodwaveproject.dto.address.AddressUpdateDto;

import java.util.List;

public interface AddressService {

    // Yangi manzil qo'shish
    AddressResponseDto create(AddressCreateDto createDto);

    // ID bo'yicha manzilni olish
    AddressDto getById(Long id);

    // Barcha manzillarni olish
    List<AddressResponseDto> getAll();

    // Shahar bo'yicha manzillarni olish
    List<AddressResponseDto> getByCity(String city);

    // Tuman bo'yicha manzillarni olish
    List<AddressResponseDto> getByDistrict(String district);

    // Mamlakat bo'yicha manzillarni olish
    List<AddressResponseDto> getByCountry(String country);

    // Manzilni yangilash
    AddressResponseDto update(Long id, AddressUpdateDto updateDto);

    // Manzilni o'chirish
    void delete(Long id);
}
