package plantseedshome.example.PBL6.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import plantseedshome.example.PBL6.DAO.entity.ProductType;
import plantseedshome.example.PBL6.Services.ProductService;
import plantseedshome.example.PBL6.dto.ProductDto;
import plantseedshome.example.PBL6.dto.ProductRequestDto;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin
@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ServletContext servletContext;

    private List<String> urlImage = new ArrayList<>();

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        List<ProductDto> productsList = productService.getAllProduct();
        return new ResponseEntity<>(productsList, HttpStatus.OK);
    }

    @GetMapping("/getProduct")
    public ResponseEntity<ProductDto> getProductWithId(@RequestParam String id) {
        ProductDto productDto = productService.findProductById(id);
        System.out.println(productDto);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("/getProductWithType")
    public ResponseEntity<List<ProductDto>> getProductWithType(@RequestParam String type) {
        List<ProductDto> productDto = productService.findProductByType(type);
        if (productDto != null) {
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found");
        }
    }

    @GetMapping("/getAllProductType")
    public ResponseEntity<List<ProductType>> getAllProducType() {
        List<ProductType> productTypes = productService.getAllProductType();
        return new ResponseEntity<>(productTypes, HttpStatus.OK);
    }

    @GetMapping("/getProductByShop")
    public ResponseEntity<List<ProductDto>> getProductByShop(@RequestParam String shopId){
        List<ProductDto> productDtoList = productService.findProductByShopId(shopId);
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @PostMapping("/addNewProduct")
    public ResponseEntity<String> createNewProduct(@RequestBody String request) {
        Gson gson = new Gson();
        ProductRequestDto productRequestDto = gson.fromJson(request, ProductRequestDto.class);
        productService.createNewProduct(productRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','SELLER')")
    @PostMapping("/addMultiProduct")
    public ResponseEntity<String> createMultiProduct(@RequestBody String request, @RequestParam String shopId) {
        Gson gson = new Gson();
       ProductRequestDto[] productRequestDtos = gson.fromJson(request, ProductRequestDto[].class);
       Arrays.stream(productRequestDtos).map(productRequestDto -> {
           productRequestDto.setShops(shopId);
            productService.createNewProduct(productRequestDto);
           return null;
       }).collect(Collectors.toList());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SELLER')")
    @PostMapping("/editProduct")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody String productDto) {
        Gson gson = new Gson();
        ProductRequestDto productRequestDto = gson.fromJson(productDto, ProductRequestDto.class);
        ProductDto response = productService.updateProduct(productRequestDto);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<String> deleteProduct(@RequestParam("productId") String productId) {
      return new ResponseEntity<>(productService.deleteProduct(productId),HttpStatus.OK);
    }

    @PostMapping("/addProductImage")
    public String saveProductImage(@RequestParam("image") MultipartFile multipartFiles) {
        return productService.saveProductImage(multipartFiles);
    }

}