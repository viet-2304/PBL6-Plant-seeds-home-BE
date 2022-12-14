package plantseedshome.example.PBL6.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import plantseedshome.example.PBL6.DAO.entity.Shops;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shops, String> {

    @Query("select s from Shops s where s.users.id = ?1")
    Shops findShopsByUserId(String userId);

    @Query("select s from Shops s where s.shopName = ?1")
    Optional<List<Shops>> findShopsByShopName(String shopName);

    @Modifying
    @Transactional
    @Query("update Shops s set s.shopName = ?2, s.address = ?3, s.phoneNumber= ?4, s.email = ?5, s.isDelete=false where s.shopId = ?1")
    void editShop(String id, String name, String address, String phoneNumber, String email);
}
