package plantseedshome.example.PBL6.Services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import plantseedshome.example.PBL6.DAO.entity.ImagesProduct;
import plantseedshome.example.PBL6.DAO.entity.ProductType;
import plantseedshome.example.PBL6.DAO.entity.Products;
import plantseedshome.example.PBL6.DAO.repository.ImagesProductRepository;
import plantseedshome.example.PBL6.DAO.repository.ProductRepository;
import plantseedshome.example.PBL6.DAO.repository.ProductTypeRepository;
import plantseedshome.example.PBL6.Services.ProductService;
import plantseedshome.example.PBL6.common.constant.ProjectConstant;
import plantseedshome.example.PBL6.dto.ProductDto;
import plantseedshome.example.PBL6.dto.ProductRequestDto;
import plantseedshome.example.PBL6.mapper.ProductMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final ProductTypeRepository productTypeRepository;

    private  final ImagesProductRepository imagesProductRepository;

    private List<String> imageProducts = new ArrayList<>();

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

    @Override
    public String createNewProduct(ProductRequestDto productRequestDto) {
        productRequestDto.setCreateDate(Date.valueOf(LocalDate.now()));
        productRepository.save(productMapper.productRequestDtoToProduct(productRequestDto));
        List<Products> products = productRepository.getProductByCreateDate(productRequestDto.getCreateDate()).get();
        System.out.println(productRequestDto.getCreateDate());
        Products product = products.get(products.size()-1);
        imagesProductRepository.save(new ImagesProduct("", imageProducts.get(0), product,null));
        return null;
    }

    @Override
    public String saveProductImage(MultipartFile multipartFiles) {
        String systemPath = System.getProperty("user.dir");
        String path = ProjectConstant.PROJECT_PATH + ProjectConstant.PRODUCT_IMAGE_PATH_FOLDER +multipartFiles.getOriginalFilename();
        if (multipartFiles != null) {
            try {
                String filePath =systemPath + path;
                multipartFiles.transferTo(Path.of(filePath));
                imageProducts.add(path);
                System.out.println(imageProducts);
                return path;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }

    private void saveImageProduct(String imageUrl, String productId){

    }

    //    @Override
//    public List<ProductDto> getListNewProduct() {
//        List<ProductDto> newProduct = productRepository.findAll().stream().map(products -> productMapper.productToProductDto(products)).collect(Collectors.toList());
//    }
}
