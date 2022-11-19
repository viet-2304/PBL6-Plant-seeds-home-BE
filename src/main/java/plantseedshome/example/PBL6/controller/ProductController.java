package plantseedshome.example.PBL6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import plantseedshome.example.PBL6.DAO.entity.ProductType;
import plantseedshome.example.PBL6.DAO.entity.Products;
import plantseedshome.example.PBL6.Services.ProductService;
import plantseedshome.example.PBL6.dto.ProductDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin
@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        List<ProductDto> productsList = productService.getAllProduct();
        return new ResponseEntity<>(productsList, HttpStatus.OK);
    }

    @GetMapping("/getProduct")
    public ResponseEntity<ProductDto> getProductWithId(@RequestParam String id) {
        System.out.print(id);
        ProductDto productDto= productService.findProductById(id);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("/getProductWithType")
    public ResponseEntity<List<ProductDto>> getProductWithType(@RequestParam String type) {
       List<ProductDto> productDto = productService.findProductByType(type);
        if (productDto != null) {
            return  new ResponseEntity<>(productDto, HttpStatus.OK);
        }
        else {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found");
        }
    }

    @GetMapping("/getAllProductType")
    public ResponseEntity<List<ProductType>> getAllProducType() {
    List<ProductType> productTypes = productService.getAllProductType();
        return new ResponseEntity<>(productTypes, HttpStatus.OK);
    }

//    @GetMapping()
//    public ResponseEntity<ProductDto> getListNewProduct() {
//        List<ProductDto> newProduct = productService;
//        return new ResponseEntity<>(newProduct, HttpStatus.OK);
//    }
}
