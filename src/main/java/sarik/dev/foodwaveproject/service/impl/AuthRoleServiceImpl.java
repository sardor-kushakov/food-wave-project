package sarik.dev.foodwaveproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwaveproject.dto.auth.role.AuthRoleCreateDto;
import sarik.dev.foodwaveproject.dto.auth.role.AuthRoleDto;
import sarik.dev.foodwaveproject.dto.auth.role.AuthRoleResponseDto;
import sarik.dev.foodwaveproject.dto.auth.role.AuthRoleUpdateDto;
import sarik.dev.foodwaveproject.entity.auth.AuthRole;
import sarik.dev.foodwaveproject.exception.ResourceNotFoundException;
import sarik.dev.foodwaveproject.mapper.AuthRoleMapper;
import sarik.dev.foodwaveproject.repository.AuthRoleRepository;
import sarik.dev.foodwaveproject.service.AuthRoleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthRoleServiceImpl implements AuthRoleService {

    private final AuthRoleRepository authRoleRepository;
    private final AuthRoleMapper authRoleMapper;

    @Override
    public AuthRoleResponseDto create(AuthRoleCreateDto createDto) {
        AuthRole authRole = authRoleMapper.fromCreateDto(createDto);
        AuthRole savedRole = authRoleRepository.save(authRole);
        return authRoleMapper.toResponseDto(savedRole);
    }

    @Override
    public AuthRoleDto getById(Integer id) {
        AuthRole authRole = authRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "ID", id));
        return authRoleMapper.toDto(authRole);
    }

    @Override
    public AuthRoleDto getByName(String name) {
        AuthRole authRole = authRoleRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "Name", name));
        return authRoleMapper.toDto(authRole);
    }

    @Override
    public List<AuthRoleResponseDto> getAll() {
        List<AuthRole> roles = authRoleRepository.findAll();
        return roles.stream()
                .map(authRoleMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthRoleResponseDto update(Integer id, AuthRoleUpdateDto updateDto) {
        AuthRole existingRole = authRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "ID", id));

        // Update fields
        authRoleMapper.fromUpdateDto(updateDto, existingRole);

        // Save updated role
        AuthRole updatedRole = authRoleRepository.save(existingRole);
        return authRoleMapper.toResponseDto(updatedRole);
    }

    @Override
    public void delete(Integer id) {
        AuthRole existingRole = authRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "ID", id));
        authRoleRepository.delete(existingRole);
    }
}
