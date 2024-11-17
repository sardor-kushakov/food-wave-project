package sarik.dev.foodwaveproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import sarik.dev.foodwaveproject.dto.auth.user.AuthUserCreateDto;
import sarik.dev.foodwaveproject.dto.auth.user.AuthUserDto;
import sarik.dev.foodwaveproject.dto.auth.user.AuthUserResponseDto;
import sarik.dev.foodwaveproject.dto.auth.user.AuthUserUpdateDto;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;

@Mapper(componentModel = "spring")
public interface AuthUserMapper {

    // AuthUserCreateDto -> AuthUser
    AuthUser fromCreateDto(AuthUserCreateDto dto);

    // AuthUser -> AuthUserDto
    AuthUserDto toDto(AuthUser authUser);

    // AuthUser -> AuthUserResponseDto
    AuthUserResponseDto toResponseDto(AuthUser authUser);

    // AuthUserUpdateDto -> AuthUser
    AuthUser fromUpdateDto(AuthUserUpdateDto dto, @MappingTarget AuthUser authUser);
}
