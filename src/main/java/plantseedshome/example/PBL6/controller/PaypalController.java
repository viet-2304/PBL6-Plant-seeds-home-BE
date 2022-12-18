package plantseedshome.example.PBL6.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import plantseedshome.example.PBL6.Services.impl.PaypalServiceImpl;
import plantseedshome.example.PBL6.dto.PaypalDto;

@Controller
@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin
public class PaypalController {

    public static final String SUCCESS_URL = "success";
    public static final String CANCEL_URL = "cancel";

    @Autowired
    PaypalServiceImpl paypalService;

    @PostMapping("/pay")
    public String payment(PaypalDto paypalDto) {
        try {
            Payment payment = paypalService.createPayment(
                    100000.0,
                    "USD",
                    "paypal",
                    "sale",
                    "test payment",
                    "http://localhost:8080/api/v1/payment" + CANCEL_URL,
                    "http://localhost:8080/" + SUCCESS_URL);

            for(Links link:payment.getLinks()){
                if(link.getRel().equals("approval_url")) {
                    System.out.println(link.getHref());
                }
            }
        }
        catch (PayPalRESTException e) {
            e.printStackTrace();
        };
        return "";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
