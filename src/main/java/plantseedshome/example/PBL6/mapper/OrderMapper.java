package plantseedshome.example.PBL6.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import plantseedshome.example.PBL6.DAO.entity.Orders;
import plantseedshome.example.PBL6.dto.OrderDto;
import plantseedshome.example.PBL6.dto.OrderResponseDto;

@Mapper
public interface OrderMapper {

    @Mapping(source = "userId", target ="users.id" )
    @Mapping(source = "orderDetailId", target = "orderDetails.id")
    Orders orderDtoToOrder(OrderDto orderDto);

    @Mapping(source = "users.id", target = "userId")
    @Mapping(source = "users.userName", target = "userName")
    @Mapping(source = "orderDetails.createDate", target = "createDate")
    @Mapping(source = "orderDetails.updateDate", target = "updateDate")
    @Mapping(source = "orderDetails.address", target = "address")
    @Mapping(source = "orderDetails.paymentMethod.paymentMethodName", target = "paymentMethod")
    @Mapping(source = "orderDetails.orderStatus.statusName", target = "orderStatus")
    OrderResponseDto orderToOrderResponseDto(Orders orders);
}
