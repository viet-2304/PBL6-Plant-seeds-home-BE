package plantseedshome.example.PBL6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import plantseedshome.example.PBL6.Services.CartService;
import plantseedshome.example.PBL6.dto.CartDto;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    @PreAuthorize("hasAnyAuthority('ADMIN','ROOT')")
    @GetMapping("/getAll")
    public ResponseEntity<List<CartDto>> getAllCart() {
       return new ResponseEntity<>(cartService.getAllCart(), HttpStatus.OK) ;
    }

    @GetMapping("")
    public ResponseEntity<CartDto> getCartWithId(@RequestParam String id) {
        return new ResponseEntity<>(cartService.getCartWithId(id), HttpStatus.OK);
    }

    @GetMapping("/getCartDetail")
    public ResponseEntity<List<CartDto>> getCartWithUserId(@RequestBody String userId) {
        return new ResponseEntity<>(cartService.getCartWithUserId(userId),HttpStatus.OK);
    }

    @PostMapping("/addToCart")
    public ResponseEntity<String> addNewProductToCart(@RequestBody CartDto cartDto) {
        cartService.addProductToCart(cartDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/editCart")
    public ResponseEntity<String> updateNumberOfProduct(@RequestBody CartDto cartDto) {
        cartService.updateProductInCart(cartDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/deleteProductInCart")
    public ResponseEntity<String> deleteProductInCart(@RequestParam String id) {
        cartService.deleteProductInCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
