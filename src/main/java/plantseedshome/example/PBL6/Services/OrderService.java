package plantseedshome.example.PBL6.Services;

import plantseedshome.example.PBL6.dto.*;

import java.text.ParseException;
import java.util.List;

public interface OrderService {
    void createOrder(OrderRequestDto orderRequestDto) throws ParseException;
    List<OrderResponseWithListProductDto> getAllOrder();
    List<OrderResponseWithListProductDto> findOrderByUserId(String userId);
    List<OrderResponseWithListProductDto> findOrderByShopId(String shopId);
    OrderResponseWithListProductDto updateOrderStatus(OrderStatusRequestDto orderStatusRequestDto);
    OrderResponseWithListProductDto getOrderDetail(String orderId);
    List<BestSellerDto> get5ProductBestSeller();
}
