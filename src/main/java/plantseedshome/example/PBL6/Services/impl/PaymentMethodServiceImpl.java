package plantseedshome.example.PBL6.Services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.entity.PaymentMethod;
import plantseedshome.example.PBL6.DAO.repository.PaymentMethodRepository;
import plantseedshome.example.PBL6.Services.PaymentMethodService;

import java.util.List;

@Service
@RequiredArgsConstructor

public class PaymentMethodServiceImpl implements PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;
    @Override
    public List<PaymentMethod> getAllPaymentMethod() {
        return paymentMethodRepository.findAll();
    }
}
