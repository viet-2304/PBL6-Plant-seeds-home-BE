package plantseedshome.example.PBL6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plantseedshome.example.PBL6.DAO.entity.OrderStatus;
import plantseedshome.example.PBL6.Services.OrderStatusService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/orderStatus")
@CrossOrigin
public class OrderStatusController {
    @Autowired
    OrderStatusService orderStatusService;

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderStatus>> getAllOrderStatus() {
        return new ResponseEntity<>(orderStatusService.getAllOrderStatus(), HttpStatus.OK);
    }
}
