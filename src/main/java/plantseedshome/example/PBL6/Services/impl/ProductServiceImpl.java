package plantseedshome.example.PBL6.Services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.entity.Products;
import plantseedshome.example.PBL6.DAO.repository.ProductRepository;
import plantseedshome.example.PBL6.Services.ProductService;
import plantseedshome.example.PBL6.dto.ProductDto;
import plantseedshome.example.PBL6.mapper.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

   private final ProductRepository productRepository;

   private final ProductMapper productMapper;
    @Override
    public List<ProductDto> getAllProduct() {
       return productRepository.findAll().stream().map(products -> productMapper.productToProductDto(products)).collect(Collectors.toList());

    }

    @Override
    public ProductDto findProductById(String id) {

        return productMapper.productToProductDto(productRepository.findById(id).get());
    }

    @Override
    public List<ProductDto> getListNewProduct() {
        List<ProductDto> newProduct = productRepository.findAll().stream().map(products -> productMapper.productToProductDto(products)).collect(Collectors.toList());
    }
}
