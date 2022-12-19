package plantseedshome.example.PBL6.controller;

import com.google.gson.Gson;
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
import plantseedshome.example.PBL6.dto.ProductRequestDto;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin
@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ServletContext servletContext;

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

    @PostMapping("/addProduct")
    public ResponseEntity<String> createNewProduct(@RequestBody String request)  {

        Gson gson = new Gson();
        ProductRequestDto productRequestDto = gson.fromJson(request, ProductRequestDto.class);
       String path = servletContext.getRealPath("/");

       if (productRequestDto.getFiles() != null) {
           try {
               String filePath = path +"/images/" +productRequestDto.getFiles()[0].getOriginalFilename();
               productRequestDto.getFiles()[0].transferTo(Path.of(filePath));
               System.out.println(filePath);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       System.out.println(productRequestDto);
//       System.out.println(new Date(String.valueOf(productRequestDto.getMFG())));
       return new ResponseEntity<>(HttpStatus.OK);

    }
}
