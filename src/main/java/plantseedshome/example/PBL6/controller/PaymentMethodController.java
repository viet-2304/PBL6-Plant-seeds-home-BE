package plantseedshome.example.PBL6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plantseedshome.example.PBL6.DAO.entity.PaymentMethod;
import plantseedshome.example.PBL6.Services.PaymentMethodService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/paymentMethod")
@CrossOrigin
public class PaymentMethodController {
    @Autowired
    PaymentMethodService paymentMethodService;

    @GetMapping("/getAllPaymentMethod")
    public ResponseEntity<List<PaymentMethod>> getAllPaymentMethod() {
        return new ResponseEntity<>(paymentMethodService.getAllPaymentMethod(), HttpStatus.OK);
    }
}
