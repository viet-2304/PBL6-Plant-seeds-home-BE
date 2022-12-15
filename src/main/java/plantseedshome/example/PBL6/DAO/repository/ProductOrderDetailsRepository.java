package plantseedshome.example.PBL6.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plantseedshome.example.PBL6.DAO.entity.ProductOrderDetails;

@Repository
public interface ProductOrderDetailsRepository extends JpaRepository<ProductOrderDetails, String> {
}
