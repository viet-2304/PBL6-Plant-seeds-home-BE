package plantseedshome.example.PBL6.Services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.entity.OrderStatus;
import plantseedshome.example.PBL6.DAO.repository.OrderStatusRepository;
import plantseedshome.example.PBL6.Services.OrderStatusService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;

    @Override
    public List<OrderStatus> getAllOrderStatus() {
      return  orderStatusRepository.findAll();
    }
}
