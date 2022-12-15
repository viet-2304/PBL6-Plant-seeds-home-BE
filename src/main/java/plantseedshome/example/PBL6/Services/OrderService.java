package plantseedshome.example.PBL6.Services;

import plantseedshome.example.PBL6.dto.OrderRequestDto;

import java.text.ParseException;

public interface OrderService {
    void createOrder(OrderRequestDto orderRequestDto) throws ParseException;
}
