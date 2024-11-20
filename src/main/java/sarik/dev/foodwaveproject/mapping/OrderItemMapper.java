package sarik.dev.foodwaveproject.mapping;

import org.mapstruct.Mapper;
import sarik.dev.foodwaveproject.entity.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItem orderItemToOrderItemHistory(OrderItem orderItem);
}
