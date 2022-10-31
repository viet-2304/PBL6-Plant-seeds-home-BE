package plantseedshome.example.PBL6.Services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.entity.ProductType;
import plantseedshome.example.PBL6.DAO.entity.Products;
//import plantseedshome.example.PBL6.DAO.repository.ImagesProductRepository;
import plantseedshome.example.PBL6.DAO.repository.ImagesProductRepository;
import plantseedshome.example.PBL6.DAO.repository.ProductRepository;
import plantseedshome.example.PBL6.DAO.repository.ProductTypeRepository;
import plantseedshome.example.PBL6.Services.ProductService;
import plantseedshome.example.PBL6.dto.ProductDto;
import plantseedshome.example.PBL6.mapper.ProductMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

   private final ProductRepository productRepository;

   private final ProductMapper productMapper;

   private final ProductTypeRepository productTypeRepository;



private  final ImagesProductRepository imagesProductRepository;
    @Override
    public List<ProductDto> getAllProduct() {
       List<ProductDto> productDtos = productRepository.findAll().stream().map(products -> productMapper.productToProductDto(products)).collect(Collectors.toList());
       imagesProductRepository.findAll();
    productDtos.forEach(product -> product.setImageURL(imagesProductRepository.findImagesProductByProductId(product.getProductId())));
    return productDtos;
    }

    @Override
    public ProductDto findProductById(String id) {
        return productMapper.productToProductDto(productRepository.findById(id).get());
    }

    @Override
    public List<ProductDto> findProductByType(String typeName) {
        List<Products> products = productRepository.findProductsByType(typeName).get();
        if(products != null) {
            return products.stream().map(product -> productMapper.productToProductDto(product)).collect(Collectors.toList());
        }
        else{
            return null;
        }
    }

    @Override
    public List<ProductType> getAllProductType() {
        return productTypeRepository.findAll();
    }

    //    @Override
//    public List<ProductDto> getListNewProduct() {
//        List<ProductDto> newProduct = productRepository.findAll().stream().map(products -> productMapper.productToProductDto(products)).collect(Collectors.toList());
//    }
}
