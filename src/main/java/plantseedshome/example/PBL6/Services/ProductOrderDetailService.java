package plantseedshome.example.PBL6.Services;

import plantseedshome.example.PBL6.DAO.entity.ProductOrderDetails;
import plantseedshome.example.PBL6.dto.ProductOrderDetailDto;

import java.util.List;

public interface ProductOrderDetailService {
    void createProductOrderService(ProductOrderDetails productOrderDetails);

    List<ProductOrderDetailDto> findProductOrderDetailDtoByOrderDetailId(String oderDetailId);
}
