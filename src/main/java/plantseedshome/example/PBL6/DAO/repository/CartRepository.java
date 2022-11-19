package plantseedshome.example.PBL6.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import plantseedshome.example.PBL6.DAO.entity.Carts;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Carts, String> {

    Optional<Carts> findById(String id);

    @Query("select c from Carts c where c.users.id= :users")
    Optional<List<Carts>> findByUserId(@Param("users") String users);

    @Modifying
    @Transactional
    @Query("update Carts c set c.numberOfProduct = ?1 where c.id = ?2 ")
    void updateProductInCart(String numberOfProduct, String id);

}
