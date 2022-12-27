package plantseedshome.example.PBL6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import plantseedshome.example.PBL6.DAO.entity.User;
import plantseedshome.example.PBL6.Services.ShopService;
import plantseedshome.example.PBL6.Services.UserService;
import plantseedshome.example.PBL6.dto.ShopDto;

import java.util.List;

@Controller
@RequestMapping("/api/v1/shop")
@CrossOrigin
public class ShopController {
    @Autowired
    ShopService shopService;

    @Autowired
    UserService userService;

    @GetMapping("/getAllShop")
    public ResponseEntity<List<ShopDto>> getAllShop() {
        return new ResponseEntity<>(shopService.getAllShop(), HttpStatus.OK);
    }

    @GetMapping("/getShopByUser")
    public ResponseEntity<ShopDto> getShopByUserId(@RequestParam String userId) {
        return new ResponseEntity<>(shopService.findByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/getShopByShopName")
    public ResponseEntity<List<ShopDto>> getShopByShopName(@RequestParam String shopName) {
        return new ResponseEntity<>(shopService.findShopByShopName(shopName), HttpStatus.OK);
    }

    @PostMapping("/addNewShop")
    public ResponseEntity<String> addNewShop(@RequestBody ShopDto shopDto) {
       String response = shopService.addNewShop(shopDto);
       if(response == "success") {
           return new ResponseEntity<>(HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.SEE_OTHER);
    }

    @PostMapping("/updateShop")
    public ResponseEntity<String> updateShop(@RequestBody ShopDto shopDto) {
        shopService.editShop(shopDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getShopById")
    public ResponseEntity<ShopDto> getShopById(@RequestParam String shopId) {
       return new ResponseEntity<>(shopService.getShopById(shopId), HttpStatus.OK);
    }
}
