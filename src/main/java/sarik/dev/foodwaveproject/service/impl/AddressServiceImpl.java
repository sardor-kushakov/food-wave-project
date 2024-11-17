package sarik.dev.foodwaveproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwaveproject.dto.address.AddressCreateDto;
import sarik.dev.foodwaveproject.dto.address.AddressDto;
import sarik.dev.foodwaveproject.dto.address.AddressResponseDto;
import sarik.dev.foodwaveproject.dto.address.AddressUpdateDto;
import sarik.dev.foodwaveproject.entity.Address;
import sarik.dev.foodwaveproject.exception.ResourceNotFoundException;
import sarik.dev.foodwaveproject.mapper.AddressMapper;
import sarik.dev.foodwaveproject.repository.AddressRepository;
import sarik.dev.foodwaveproject.service.AddressService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public AddressResponseDto create(AddressCreateDto createDto) {
        Address address = addressMapper.fromCreateDto(createDto);
        Address savedAddress = addressRepository.save(address);
        return addressMapper.toResponseDto(savedAddress);
    }

    @Override
    public AddressDto getById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "ID", id));
        return addressMapper.toDto(address);
    }

    @Override
    public List<AddressResponseDto> getAll() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(addressMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AddressResponseDto> getByCity(String city) {
        List<Address> addresses = addressRepository.findByCity(city);
        if (addresses.isEmpty()) {
            throw new ResourceNotFoundException("Address", "City", city);
        }
        return addresses.stream()
                .map(addressMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AddressResponseDto> getByDistrict(String district) {
        List<Address> addresses = addressRepository.findByDistrict(district);
        if (addresses.isEmpty()) {
            throw new ResourceNotFoundException("Address", "District", district);
        }
        return addresses.stream()
                .map(addressMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AddressResponseDto> getByCountry(String country) {
        List<Address> addresses = addressRepository.findByCountry(country);
        if (addresses.isEmpty()) {
            throw new ResourceNotFoundException("Address", "Country", country);
        }
        return addresses.stream()
                .map(addressMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AddressResponseDto update(Long id, AddressUpdateDto updateDto) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "ID", id));

        addressMapper.fromUpdateDto(updateDto, existingAddress);

        Address updatedAddress = addressRepository.save(existingAddress);
        return addressMapper.toResponseDto(updatedAddress);
    }

    @Override
    public void delete(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "ID", id));
        addressRepository.delete(address);
    }
}
