package plantseedshome.example.PBL6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import plantseedshome.example.PBL6.Services.OrderService;
import plantseedshome.example.PBL6.dto.OrderDto;
import plantseedshome.example.PBL6.dto.OrderRequestDto;
import plantseedshome.example.PBL6.dto.OrderResponseWithListProductDto;

import java.text.ParseException;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<String> CreateNewOrder(@RequestBody OrderRequestDto orderRequestDto) throws ParseException {
        orderService.createOrder(orderRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getAllOrder")
    public ResponseEntity<List<OrderResponseWithListProductDto>> getAllOrder() {
        return new ResponseEntity<>(orderService.getAllOrder(), HttpStatus.OK);
    }

}
