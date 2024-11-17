package sarik.dev.foodwaveproject.mapper;

import org.mapstruct.Mapper;
import sarik.dev.foodwaveproject.dto.payment.PaymentCreateDto;
import sarik.dev.foodwaveproject.dto.payment.PaymentDto;
import sarik.dev.foodwaveproject.dto.payment.PaymentResponseDto;
import sarik.dev.foodwaveproject.dto.payment.PaymentUpdateDto;
import sarik.dev.foodwaveproject.entity.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    // PaymentCreateDto -> Payment
    Payment fromCreateDto(PaymentCreateDto dto);

    // Payment -> PaymentDto
    PaymentDto toDto(Payment payment);

    // Payment -> PaymentResponseDto
    PaymentResponseDto toResponseDto(Payment payment);

    // PaymentUpdateDto -> Payment
    Payment fromUpdateDto(PaymentUpdateDto dto, Payment payment);
}
