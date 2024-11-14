package sarik.dev.foodwaveproject.mapper;

import jakarta.validation.Valid;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import sarik.dev.foodwaveproject.dto.authUserDto.AuthUserDTO;
import sarik.dev.foodwaveproject.dto.authUserDto.AuthUserResponseDTO;
import sarik.dev.foodwaveproject.dto.authUserDto.CreateAuthUserDTO;
import sarik.dev.foodwaveproject.dto.authUserDto.UpdateAuthUserDTO;
import sarik.dev.foodwaveproject.entity.auth.AuthUser;

import java.util.List;

@Mapper
public interface AuthUserMapper {


    public AuthUserResponseDTO toResponseDTO(@Valid AuthUser authUser);

    public List<AuthUserResponseDTO> toResponseDTOList(@Valid List<AuthUser> authUsers);

    public AuthUserResponseDTO toUpdateDTO(@Valid UpdateAuthUserDTO dto);

    public AuthUserResponseDTO toCreateDTO(@Valid CreateAuthUserDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AuthUser partialUpdateAuthUser(UpdateAuthUserDTO dto, @MappingTarget AuthUser user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AuthUser partialCreateAuthUser(CreateAuthUserDTO dto, @MappingTarget AuthUser user);


}
