package plantseedshome.example.PBL6.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import plantseedshome.example.PBL6.DAO.entity.ProductOrderDetails;

import java.util.List;

@Repository
public interface ProductOrderDetailsRepository extends JpaRepository<ProductOrderDetails, String> {

    @Query("select p from ProductOrderDetails p where p.orderDetails.id = ?1")
    List<ProductOrderDetails> findProductOrderDetailsByOrderDetailId(String orderDetailId);
}
