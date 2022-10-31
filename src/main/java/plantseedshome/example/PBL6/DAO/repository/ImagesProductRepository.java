package plantseedshome.example.PBL6.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import plantseedshome.example.PBL6.DAO.entity.ImagesProduct;

import java.util.List;

@Repository
public interface ImagesProductRepository extends JpaRepository<ImagesProduct, String> {
    @Query("select i.imageUrl from ImagesProduct i where i.products.productId = :productId")
    public List<String> findImagesProductByProductId(@RequestParam("productId") String productId);
}
