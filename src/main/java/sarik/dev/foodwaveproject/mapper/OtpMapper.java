package sarik.dev.foodwaveproject.mapper;

import org.mapstruct.Mapper;
import sarik.dev.foodwaveproject.dto.otp.OtpCreateDto;
import sarik.dev.foodwaveproject.dto.otp.OtpDto;
import sarik.dev.foodwaveproject.dto.otp.OtpResponseDto;
import sarik.dev.foodwaveproject.dto.otp.OtpVerifyDto;
import sarik.dev.foodwaveproject.entity.Otp;

@Mapper(componentModel = "spring")
public interface OtpMapper {

    // OtpCreateDto -> Otp
    Otp fromCreateDto(OtpCreateDto dto);

    // Otp -> OtpDto
    OtpDto toDto(Otp otp);

    // Otp -> OtpResponseDto
    OtpResponseDto toResponseDto(Otp otp);

    // OtpVerifyDto -> Otp
    Otp fromVerifyDto(OtpVerifyDto dto, Otp otp);
}
