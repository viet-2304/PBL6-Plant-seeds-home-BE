package plantseedshome.example.PBL6.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import plantseedshome.example.PBL6.DAO.entity.Products;
import plantseedshome.example.PBL6.dto.ProductDto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, String> {
    Optional<Products> findById(String id);

    @Query("SELECT p from Products p where p.productType.Name = :type")
    Optional<List<Products>> findProductsByType(@Param("type") String type);

    @Query("select  p from  Products p where p.createDate = ?1 ")
    Optional<List<Products>> getProductByCreateDate(Date createDate);

    @Query("select p from Products p where p.shops.shopId= ?1")
    Optional<List<Products>> findProductsByShopId(String shopId);
}
