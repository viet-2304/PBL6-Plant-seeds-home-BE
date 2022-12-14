package plantseedshome.example.PBL6.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.entity.ProductOrderDetails;
import plantseedshome.example.PBL6.DAO.repository.ProductOrderDetailsRepository;
import plantseedshome.example.PBL6.Services.ProductOrderDetailService;
import plantseedshome.example.PBL6.Services.ProductService;

@Service
public class ProductOrderDetailServiceImpl implements ProductOrderDetailService {
    @Autowired
    ProductOrderDetailsRepository productOrderDetailsRepository;
    @Override
    public void createProductOrderService(ProductOrderDetails productOrderDetails) {
        productOrderDetailsRepository.save(productOrderDetails);

    }
}
