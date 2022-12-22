package plantseedshome.example.PBL6.Services;

import plantseedshome.example.PBL6.dto.OrderRequestDto;
import plantseedshome.example.PBL6.dto.OrderResponseDto;
import plantseedshome.example.PBL6.dto.OrderResponseWithListProductDto;

import java.text.ParseException;
import java.util.List;

public interface OrderService {
    void createOrder(OrderRequestDto orderRequestDto) throws ParseException;
    List<OrderResponseWithListProductDto> getAllOrder();
    List<OrderResponseWithListProductDto> findOrderByUserId(String userId);
}
