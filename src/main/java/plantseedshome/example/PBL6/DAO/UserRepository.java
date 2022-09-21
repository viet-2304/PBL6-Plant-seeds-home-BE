package plantseedshome.example.PBL6.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import plantseedshome.example.PBL6.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}
