package sarik.dev.foodwaveproject.mapping;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sarik.dev.foodwaveproject.entity.Order;
import sarik.dev.foodwaveproject.entity.OrderHistory;
import sarik.dev.foodwaveproject.entity.OrderItem;

import java.util.List;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderMapper {

    @Mapping(target = "saveTime", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "orderItems", source = "orderItems")
    OrderHistory orderToOrderHistory(Order order);

    @IterableMapping(qualifiedByName = "orderItemToOrderItemHistory")
    List<OrderItem> mapOrderItems(List<OrderItem> orderItems);
}
