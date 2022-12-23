package plantseedshome.example.PBL6.Services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.entity.OrderDetails;
import plantseedshome.example.PBL6.DAO.repository.OrdersDetailRepository;
import plantseedshome.example.PBL6.Services.OrderDetailService;
import plantseedshome.example.PBL6.dto.OrderDetailDto;
import plantseedshome.example.PBL6.mapper.OrderDetailMapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrdersDetailRepository ordersDetailRepository;

    private final OrderDetailMapper orderDetailMapper;
    @Override
    public OrderDetailDto CreateOrderDetail(OrderDetailDto orderDetailDto)  {
        ordersDetailRepository.save(orderDetailMapper.orderDetailDtoToOrderDetail(orderDetailDto));
        List<OrderDetails> orderDetailsList = ordersDetailRepository.getOrderDetailsByCreateDate(orderDetailDto.getCreateDate()).get();

        return orderDetailMapper.orderDetailToOrderDetailDto(
                orderDetailsList.get(orderDetailsList.size()-1)  );
    }
}
