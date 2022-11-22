package plantseedshome.example.PBL6.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import plantseedshome.example.PBL6.DAO.entity.Shops;

@Repository
public interface ShopRepository extends JpaRepository<Shops, String> {

//    @Query("select s from Shops s where s.users = ?1")
//    Shops findShopsByUserId(String userId);
}
