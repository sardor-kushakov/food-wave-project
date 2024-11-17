package sarik.dev.foodwaveproject.mapper;

import org.mapstruct.Mapper;
import sarik.dev.foodwaveproject.dto.notification.NotificationCreateDto;
import sarik.dev.foodwaveproject.dto.notification.NotificationDto;
import sarik.dev.foodwaveproject.dto.notification.NotificationResponseDto;
import sarik.dev.foodwaveproject.dto.notification.NotificationUpdateDto;
import sarik.dev.foodwaveproject.entity.Notification;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    // NotificationCreateDto -> Notification
    Notification fromCreateDto(NotificationCreateDto dto);

    // Notification -> NotificationDto
    NotificationDto toDto(Notification notification);

    // Notification -> NotificationResponseDto
    NotificationResponseDto toResponseDto(Notification notification);

    // NotificationUpdateDto -> Notification
    Notification fromUpdateDto(NotificationUpdateDto dto, Notification notification);
}
