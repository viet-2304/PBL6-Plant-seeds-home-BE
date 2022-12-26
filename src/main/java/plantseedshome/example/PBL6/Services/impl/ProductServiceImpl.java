package plantseedshome.example.PBL6.Services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import plantseedshome.example.PBL6.DAO.entity.ImagesProduct;
import plantseedshome.example.PBL6.DAO.entity.ProductType;
import plantseedshome.example.PBL6.DAO.entity.Products;
import plantseedshome.example.PBL6.DAO.repository.*;
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

    private final ProductOrderDetailsRepository productOrderDetailsRepository;

    private final CartRepository cartRepository;

    private List<String> imageProducts = new ArrayList<>();

    @Override
    public List<ProductDto> getAllProduct() {
       List<ProductDto> productDtos = productRepository.findAll().stream().map(products -> productMapper.productToProductDto(products)).collect(Collectors.toList());
       imagesProductRepository.findAll();
       productDtos.forEach(product -> product.setImagesUrl(imagesProductRepository.findImagesProductByProductId(product.getProductId())));
        return productDtos;
    }

    @Override
    public ProductDto findProductById(String id) {
        List<String> imagesUrl = imagesProductRepository.findImagesProductByProductId(id);
        if(productRepository.findById(id).isPresent()) {

        ProductDto productDto = productMapper.productToProductDto(productRepository.findById(id).get());
        productDto.setImagesUrl(imagesUrl);
        return productDto;
        }
    return null;
    }

    @Override
    public List<ProductDto> findProductByType(String typeName) {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Products> products = productRepository.findProductsByType(typeName).get();
        if(products != null) {
            products.forEach(products1 -> {
                ProductDto productDto = productMapper.productToProductDto(products1);
                productDto.setImagesUrl(imagesProductRepository.findImagesProductByProductId(products1.getProductId()));
                productDtoList.add(productDto);
            });
            return  productDtoList;
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
        Products product = products.get(products.size()-1);
        if(productRequestDto.getImagesUrl() != null) {
            productRequestDto.getImagesUrl().forEach(image -> {
                imagesProductRepository.save(new ImagesProduct("", image, product,null));
            });
        }
        else {
            imagesProductRepository.save(new ImagesProduct("", "", product,null));
        }
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
                return path;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }

    @Override
    public List<ProductDto> findProductByShopId(String shopId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Products> products = productRepository.findProductsByShopId(shopId).get();
        products.forEach(products1 -> {
            ProductDto productDto = productMapper.productToProductDto(products1);
            productDto.setImagesUrl(imagesProductRepository.findImagesProductByProductId(products1.getProductId()));
            productDtoList.add(productDto);
        });
        return productDtoList;
    }

    @Override
    public ProductDto updateProduct(@RequestBody ProductRequestDto productRequestDto) {
        Products products = productRepository.findById(productRequestDto.getProductId()).get();
        if (products != null) {
            Products tempProduct = productMapper.productRequestDtoToProduct(productRequestDto);
            imagesProductRepository.removeImagesProductByProductId(productRequestDto.getProductId());
            productRequestDto.getImagesUrl().forEach(imageProduct -> {
                imagesProductRepository.save(new ImagesProduct("", imageProduct, products, null));
            });
            productRepository.save(tempProduct);
            ProductDto productDto =productMapper.productToProductDto(products);
            productDto.setImagesUrl(productRequestDto.getImagesUrl());
            return productDto;
        }
        return null;
    }

    @Override
    public String deleteProduct(String productId) {
        if(productRepository.findById(productId).isPresent()) {
            productOrderDetailsRepository.deleteProductOrderDetailsByProductId(productId);
            cartRepository.deleteCartWithProduct(productId);
            imagesProductRepository.removeImagesProductByProductId(productId);
            productRepository.deleteById(productId);
            return "success";
        }
        return "false";
    }
}
