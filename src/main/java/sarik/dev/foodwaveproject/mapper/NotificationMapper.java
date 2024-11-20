package sarik.dev.foodwaveproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sarik.dev.foodwaveproject.dto.notification.NotificationCreateDto;
import sarik.dev.foodwaveproject.dto.notification.NotificationDto;
import sarik.dev.foodwaveproject.dto.notification.NotificationResponseDto;
import sarik.dev.foodwaveproject.entity.Notification;

@Mapper(componentModel = "spring", uses = AuthUserMapper.class)
public interface NotificationMapper {

    @Mapping(source = "userId", target = "user.id") // `userId` ni `user.id` ga o‘tkazish
    Notification fromCreateDto(NotificationCreateDto dto);

    NotificationDto toDto(Notification notification);

    NotificationResponseDto toResponseDto(Notification notification);
}
