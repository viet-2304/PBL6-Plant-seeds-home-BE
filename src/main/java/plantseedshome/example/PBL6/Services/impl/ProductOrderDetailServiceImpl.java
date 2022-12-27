package plantseedshome.example.PBL6.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.entity.ProductOrderDetails;
import plantseedshome.example.PBL6.DAO.repository.ProductOrderDetailsRepository;
import plantseedshome.example.PBL6.Services.ProductOrderDetailService;
import plantseedshome.example.PBL6.Services.ProductService;
import plantseedshome.example.PBL6.dto.ProductOrderDetailDto;
import plantseedshome.example.PBL6.mapper.ProductOrderDetailMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductOrderDetailServiceImpl implements ProductOrderDetailService {
    @Autowired
    ProductOrderDetailsRepository productOrderDetailsRepository;

    @Autowired
    ProductOrderDetailMapper productOrderDetailMapper;

    @Override
    public void createProductOrderService(ProductOrderDetails productOrderDetails) {
        productOrderDetailsRepository.save(productOrderDetails);

    }

    @Override
    public List<ProductOrderDetailDto> findProductOrderDetailDtoByOrderDetailId(String oderDetailId) {
        List<ProductOrderDetailDto> productOrderDetailDtoList = new ArrayList<>();
        List<ProductOrderDetails> productOrderDetails = productOrderDetailsRepository.findProductOrderDetailsByOrderDetailId(oderDetailId);
        if (productOrderDetails.isEmpty()) {
            return productOrderDetailDtoList;
        }
             productOrderDetails.forEach(productOrderDetail -> {
                 productOrderDetailDtoList.add(productOrderDetailMapper.productOrderDetailToProductOrderDetailDto(productOrderDetail));
             });
              return productOrderDetailDtoList;
    }
}
