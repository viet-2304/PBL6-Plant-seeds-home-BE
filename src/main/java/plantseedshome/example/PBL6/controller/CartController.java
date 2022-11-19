package plantseedshome.example.PBL6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/getAll")
    public ResponseEntity<List<CartDto>> getAllCart() {
       return new ResponseEntity<>(cartService.getAllCart(), HttpStatus.OK) ;
    }

    @GetMapping("")
    public ResponseEntity<CartDto> getCartWithId(@RequestParam String id) {
        return new ResponseEntity<>(cartService.getCartWithId(id), HttpStatus.OK);
    }
//    @PostMapping("/createCart")
//    public ResponseEntity<String> createCart(@RequestBody CartDto cartDto) {
//        cartService.createCart(cartDto);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }



}
