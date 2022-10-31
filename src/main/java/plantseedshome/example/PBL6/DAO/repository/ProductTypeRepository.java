package plantseedshome.example.PBL6.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plantseedshome.example.PBL6.DAO.entity.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, String> {

}
