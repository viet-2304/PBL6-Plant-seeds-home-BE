package plantseedshome.example.PBL6.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import plantseedshome.example.PBL6.DAO.entity.Carts;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Carts, String> {
//    Optional<List<Carts>> findAllByUserId(String userId);

    Optional<Carts> findById(String id);

    @Query("select c from Carts c where c.users.id= :users")
    Optional<List<Carts>> findByUserId(@Param("users") String users);
//
//    void deleteById(String id);



}
