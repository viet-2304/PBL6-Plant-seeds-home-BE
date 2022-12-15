package plantseedshome.example.PBL6.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import plantseedshome.example.PBL6.DAO.entity.OrderDetails;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersDetailRepository extends JpaRepository<OrderDetails, String> {

    @Query("select o from OrderDetails o  where o.createDate = ?1")
    Optional<List<OrderDetails>> getOrderDetailsByCreateDate(Date createDate);
}
