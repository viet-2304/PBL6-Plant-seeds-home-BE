package plantseedshome.example.PBL6.Services;

import plantseedshome.example.PBL6.DAO.entity.ProductType;
import plantseedshome.example.PBL6.DAO.entity.Products;
import plantseedshome.example.PBL6.dto.ProductDto;
import plantseedshome.example.PBL6.dto.ProductRequestDto;

import java.util.List;


public interface ProductService {
    List<ProductDto> getAllProduct();

    ProductDto findProductById(String id);

    List<ProductDto> findProductByType(String typeName);

    List<ProductType> getAllProductType();

    String createNewProduct(ProductRequestDto productRequestDto);
//    List<ProductDto> getListNewProduct();
}
