package plantseedshome.example.PBL6.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import plantseedshome.example.PBL6.Services.CartService;
import plantseedshome.example.PBL6.Services.OrderService;
import plantseedshome.example.PBL6.Services.impl.PaypalServiceImpl;
import plantseedshome.example.PBL6.dto.OrderRequestDto;
import plantseedshome.example.PBL6.dto.PaypalDto;

import java.text.ParseException;

@Controller
@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin
public class PaypalController {

    public static final String SUCCESS_URL = "paymentSuccess";
    public static final String CANCEL_URL = "paymentCancel";

    @Autowired
    PaypalServiceImpl paypalService;

    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;

    private OrderRequestDto orderRequestDto = new OrderRequestDto();
    @PostMapping("/pay")
    public ResponseEntity<String> payment(@RequestBody  PaypalDto paypalDto) throws ParseException {
         String responseUrl = "";
          orderRequestDto = paypalDto.getOrder();
        try {
            Payment payment = paypalService.createPayment(
                    paypalDto.getPrice(),
                   "USD",
                    paypalDto.getMethod(),
                    "sale",
                    paypalDto.getDescription(),
                    "http://localhost:3000/api/v1/" + CANCEL_URL,
                    "http://localhost:3000/api/v1/" + SUCCESS_URL);

            for(Links link:payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return new ResponseEntity<>(link.getHref(), HttpStatus.OK);
                }
            }
        }
        catch (PayPalRESTException e) {
            e.printStackTrace();
        };
        return new ResponseEntity<>("Error", HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId ,orderRequestDto);
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException | ParseException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
