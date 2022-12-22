package plantseedshome.example.PBL6.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.entity.*;
import plantseedshome.example.PBL6.DAO.repository.CartRepository;
import plantseedshome.example.PBL6.DAO.repository.OrdersRepository;
import plantseedshome.example.PBL6.DAO.repository.ProductRepository;
import plantseedshome.example.PBL6.Services.*;
import plantseedshome.example.PBL6.dto.*;
import plantseedshome.example.PBL6.mapper.OrderMapper;
import plantseedshome.example.PBL6.mapper.ProductMapper;
import plantseedshome.example.PBL6.mapper.ProductOrderDetailMapper;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    CartService cartService;

    @Autowired
    ProductOrderDetailService productOrderDetailService;

    @Autowired
    ProductOrderDetailMapper productOrderDetailMapper;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<OrderResponseWithListProductDto> findOrderByUserId(String userId) {
        List<OrderResponseWithListProductDto> orderResponseWithListProductDtos = new ArrayList<>();
        List<Orders> ordersList = ordersRepository.findByUserId(userId).get();
        ordersList.forEach(orders -> {
            OrderResponseDto orderResponseDto = orderMapper.orderToOrderResponseDto(orders);
            List<ProductResponseWithOrderDto> productResponseWithOrderDtoList = getProductResponseWithOrderDto(orders.getOrderDetails().getId());
            orderResponseWithListProductDtos.add(new OrderResponseWithListProductDto(orderResponseDto,productResponseWithOrderDtoList));
        });
        return orderResponseWithListProductDtos;
    }

    @Override
    public List<OrderResponseWithListProductDto> getAllOrder() {
        List<OrderResponseWithListProductDto> orderResponseWithListProductDtos = new ArrayList<>();
        List<Orders> ordersList = ordersRepository.findAll();
        ordersList.forEach(orders -> {
           OrderResponseDto orderResponseDto = orderMapper.orderToOrderResponseDto(orders);
          List<ProductResponseWithOrderDto> productResponseWithOrderDtoList = getProductResponseWithOrderDto(orders.getOrderDetails().getId());
          orderResponseWithListProductDtos.add(new OrderResponseWithListProductDto(orderResponseDto,productResponseWithOrderDtoList));
        });
        return orderResponseWithListProductDtos;
    }

    @Override
    public void createOrder(OrderRequestDto orderRequestDto) {
        String orderDetailId = saveOrderDetail(orderRequestDto.getPaymentMethodId(), orderRequestDto.getAddress());
        saveProductOrderDetail(orderRequestDto.getListCartId(), orderDetailId);
        String cartId = orderRequestDto.getListCartId().get(0);
        saveOrder(cartId, orderRequestDto.getTotal(), orderDetailId);
    }

    private String saveOrderDetail( String paymentMethod, String address) {
        OrderDetailDto orderDetailDto = new OrderDetailDto(
                "",
                Date.valueOf(LocalDate.now()),
                Date.valueOf(LocalDate.now()),
                paymentMethod,
                "1",
                address);
        System.out.println(orderDetailDto.getAddress());
       return orderDetailService.CreateOrderDetail(orderDetailDto).getOrderDetailId();
    }

    private void saveProductOrderDetail(List<String> listCartId, String orderDetailId) {
        listCartId.forEach(cartId -> {
          CartResponseDto cartResponseDto = cartService.getCartWithId(cartId);
            ProductOrderDetailDto productOrderDetailDto =
                    new ProductOrderDetailDto("",
                                        Integer.parseInt(cartResponseDto.numberOfProduct),
                                        calculatePriceOfProducts(
                                                cartResponseDto.getProduct().getPrice(),
                                                Integer.valueOf(cartResponseDto.numberOfProduct)
                                        ),
                                        orderDetailId,
                                        cartResponseDto.getProduct().getProductId()
                    );
            productOrderDetailService.createProductOrderService(productOrderDetailMapper.productOrderDetailDtoToProductOrderDetail(productOrderDetailDto));
        });
    }

    private  int calculatePriceOfProducts(int price, int number) {
        return price*number;
    }

    private void saveOrder(String cartId,int total, String orderDetailId) {
        CartDto cartDto = cartService.findCartDtoByCartId(cartId);
        OrderDto orderDto = new OrderDto("", total, cartDto.getUserId(), orderDetailId);
        ordersRepository.save(orderMapper.orderDtoToOrder(orderDto));
    }

    private List<ProductResponseWithOrderDto> getProductResponseWithOrderDto(String orderDetailId){
       List<ProductOrderDetailDto> listProductOrderDetailDto = productOrderDetailService.findProductOrderDetailDtoByOrderDetailId(orderDetailId);
       System.out.println(listProductOrderDetailDto);
       List<ProductResponseWithOrderDto> productResponseWithOrderDtoList = new ArrayList<>();

       listProductOrderDetailDto.forEach(productOrderDetailDto -> {
           Products products = productRepository.findById(productOrderDetailDto.getProductId()).get();
           System.out.println(products.getProductId());
            ProductResponseWithOrderDto productResponseWithOrderDto = new ProductResponseWithOrderDto(
                    products.getProductId(),
                    products.getProductName(),
                    products.getShops().getShopId(),
                    products.getShops().getShopName(),
                    productOrderDetailDto.getNumber(),
                    productOrderDetailDto.getTotalOfProduct()
            );
           productResponseWithOrderDtoList.add(productResponseWithOrderDto);
       });

       return productResponseWithOrderDtoList;
    }
}
