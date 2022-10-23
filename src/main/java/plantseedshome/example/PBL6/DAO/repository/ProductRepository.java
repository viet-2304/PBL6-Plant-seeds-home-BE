package plantseedshome.example.PBL6.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plantseedshome.example.PBL6.DAO.entity.Products;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, String> {
    Optional<Products> findById(String id);
}
