package plantseedshome.example.PBL6.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.entity.ProductOrderDetails;
import plantseedshome.example.PBL6.DAO.repository.ProductOrderDetailsRepository;
import plantseedshome.example.PBL6.DAO.repository.ProductRepository;
import plantseedshome.example.PBL6.Services.ProductOrderDetailService;
import plantseedshome.example.PBL6.Services.ProductService;
import plantseedshome.example.PBL6.dto.BestSellerDto;
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

    @Autowired
    ProductService productService;

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

    @Override
    public List<BestSellerDto> getBestSellerProduct() {
        List<BestSellerDto> bestSellerDtos = new ArrayList<>();
       List<String> res = productOrderDetailsRepository.getCountOfProductInOrder();
       for(int i=0 ; i<res.size() ; i++) {
           String productId = res.get(i).split(",")[1];
           int sellerNumber = Integer.parseInt(res.get(i).split(",")[0]);
           bestSellerDtos.add(new BestSellerDto(productService.findProductById(productId), sellerNumber));
       }
       return  bestSellerDtos;
    }
}
