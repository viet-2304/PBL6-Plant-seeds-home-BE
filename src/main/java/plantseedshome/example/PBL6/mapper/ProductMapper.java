package plantseedshome.example.PBL6.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import plantseedshome.example.PBL6.DAO.entity.Products;
import plantseedshome.example.PBL6.dto.ProductDto;
import plantseedshome.example.PBL6.dto.ProductRequestDto;
import plantseedshome.example.PBL6.dto.ProductResponseWithOrderDto;

import java.util.List;

@Mapper
public interface ProductMapper {
//    Products productDtoToProduct(ProductDto productDto);
    @Mapping(source = "shops.shopName", target = "shops")
    @Mapping(source = "productType.name", target = "productType")
    @Mapping(source = "productName", target = "productName")
    ProductDto productToProductDto(Products products);

    @InheritInverseConfiguration(name = "productToProductDto")
    Products productDtoToProducts(ProductDto productDto);

        @Mapping(source = "shops", target = "shops.shopId")
    @Mapping(source = "productType", target = "productType.productTypeId")
    @Mapping(source = "productName", target = "productName")
    Products productRequestDtoToProduct(ProductRequestDto productRequestDto);
}
