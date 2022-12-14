package plantseedshome.example.PBL6.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import plantseedshome.example.PBL6.DAO.entity.Orders;
import plantseedshome.example.PBL6.dto.OrderDto;

@Mapper
public interface OrderMapper {

    @Mapping(source = "userId", target ="users.id" )
    @Mapping(source = "orderDetailId", target = "orderDetails.id")
    Orders orderDtoToOrder(OrderDto orderDto);
}
