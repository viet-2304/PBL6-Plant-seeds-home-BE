package plantseedshome.example.PBL6.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plantseedshome.example.PBL6.DAO.entity.Roles;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(String roleName);
}
