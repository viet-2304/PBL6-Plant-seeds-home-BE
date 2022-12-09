package plantseedshome.example.PBL6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import plantseedshome.example.PBL6.dto.OrderDto;

@Controller
@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin
public class OrderController {

    @PostMapping("/CreateOrder")
    public ResponseEntity<String> CreateNewOrder(@RequestBody OrderDto orderDto) {
        System.out.println(orderDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
