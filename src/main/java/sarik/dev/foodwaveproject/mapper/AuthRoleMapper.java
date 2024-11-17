package sarik.dev.foodwaveproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import sarik.dev.foodwaveproject.dto.auth.role.AuthRoleCreateDto;
import sarik.dev.foodwaveproject.dto.auth.role.AuthRoleDto;
import sarik.dev.foodwaveproject.dto.auth.role.AuthRoleResponseDto;
import sarik.dev.foodwaveproject.dto.auth.role.AuthRoleUpdateDto;
import sarik.dev.foodwaveproject.entity.auth.AuthRole;

@Mapper(componentModel = "spring")
public interface AuthRoleMapper {

    // AuthRoleCreateDto -> AuthRole
    AuthRole fromCreateDto(AuthRoleCreateDto dto);

    // AuthRole -> AuthRoleDto
    AuthRoleDto toDto(AuthRole authRole);

    // AuthRole -> AuthRoleResponseDto
    AuthRoleResponseDto toResponseDto(AuthRole authRole);

    // AuthRoleUpdateDto -> AuthRole
    @Mapping(target = "id", ignore = true)
    AuthRole fromUpdateDto(AuthRoleUpdateDto dto, @MappingTarget AuthRole authRole);
}
