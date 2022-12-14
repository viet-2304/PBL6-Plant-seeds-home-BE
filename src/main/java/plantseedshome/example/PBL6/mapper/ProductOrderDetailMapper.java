package plantseedshome.example.PBL6.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import plantseedshome.example.PBL6.DAO.entity.ProductOrderDetails;
import plantseedshome.example.PBL6.dto.ProductOrderDetailDto;

@Mapper
public interface ProductOrderDetailMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "orderDetailId", target = "orderDetails.id")
    @Mapping(source = "productId", target = "products.productId")
    @Mapping(source = "totalOfProduct", target = "total")
    ProductOrderDetails productOrderDetailDtoToProductOrderDetail(ProductOrderDetailDto productOrderDetailDto);

    @InheritInverseConfiguration(name = "productOrderDetailDtoToProductOrderDetail")
    ProductOrderDetailDto productOrderDetailToProductOrderDetailDto(ProductOrderDetails productOrderDetails);
}
