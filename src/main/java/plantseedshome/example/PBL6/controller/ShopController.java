package plantseedshome.example.PBL6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import plantseedshome.example.PBL6.Services.ShopService;
import plantseedshome.example.PBL6.dto.ShopDto;

import java.util.List;

@Controller
@RequestMapping("/api/v1/shop")
@CrossOrigin
public class ShopController {
    @Autowired
    ShopService shopService;

    @GetMapping("/getAllShop")
    public ResponseEntity<List<ShopDto>> getAllShop() {
        return new ResponseEntity<>(shopService.getAllShop(), HttpStatus.OK);
    }

    @GetMapping("/getShopByUser")
    public ResponseEntity<ShopDto> getShopByUserId(@RequestParam String userId) {
        return new ResponseEntity<>(shopService.findByUserId(userId), HttpStatus.OK);
    }
}
