package sarik.dev.foodwaveproject.mapper;

import jakarta.validation.Valid;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import sarik.dev.foodwaveproject.dto.auth.user.AuthUserResponseDto;
import sarik.dev.foodwaveproject.dto.auth.user.CreateAuthUserDto;
import sarik.dev.foodwaveproject.dto.auth.user.UpdateAuthUserDto;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthUserMapper {


    public AuthUserResponseDto toResponseDTO(@Valid AuthUser authUser);

    public List<AuthUserResponseDto> toResponseDTOList(@Valid List<AuthUser> authUsers);

    public AuthUserResponseDto toUpdateDTO(@Valid UpdateAuthUserDto dto);

    public AuthUserResponseDto toCreateDTO(@Valid CreateAuthUserDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AuthUser partialUpdateAuthUser(UpdateAuthUserDto dto, @MappingTarget AuthUser user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AuthUser partialCreateAuthUser(CreateAuthUserDto dto, @MappingTarget AuthUser user);


}
