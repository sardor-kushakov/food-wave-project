package sarik.dev.foodwaveproject.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import sarik.dev.foodwaveproject.entity.Order;
import sarik.dev.foodwaveproject.entity.OrderHistory;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "orderItems", target = "orderItems")
    @Mapping(source = "orderDate", target = "orderDate")
    @Mapping(source = "payment", target = "payment")
    @Mapping(source = "totalAmount", target = "totalAmount")
    OrderHistory orderToOrderHistory(Order order);
}
