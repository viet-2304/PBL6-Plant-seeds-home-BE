package plantseedshome.example.PBL6.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import plantseedshome.example.PBL6.DAO.entity.Orders;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, String> {

    @Query("SELECT o from Orders o where o.users.id= ?1")
    Optional<List<Orders>> findByUserId(String userId);
}
