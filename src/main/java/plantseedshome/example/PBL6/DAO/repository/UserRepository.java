package plantseedshome.example.PBL6.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plantseedshome.example.PBL6.DAO.entity.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

//    Optional<User> findByPassword(String password);
    Optional<User> findByUserName(String userName);
//    Boolean existsByEmail(String email);
}
