package plantseedshome.example.PBL6.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import plantseedshome.example.PBL6.DAO.entity.OrderDetails;
import plantseedshome.example.PBL6.dto.OrderDetailDto;

@Mapper
public interface OrderDetailMapper {

    @Mapping(source = "orderDetailId", target = "id")
    @Mapping(source = "paymentMethod",target = "paymentMethod.paymentMethodId")
    @Mapping(source = "orderStatus", target = "orderStatus.statusId")
    @Mapping(source = "address", target = "address")
    OrderDetails orderDetailDtoToOrderDetail(OrderDetailDto orderDetailDto);

    @InheritInverseConfiguration(name = "orderDetailDtoToOrderDetail")
    OrderDetailDto orderDetailToOrderDetailDto(OrderDetails orderDetails);
}
