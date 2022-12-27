package plantseedshome.example.PBL6.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import plantseedshome.example.PBL6.DAO.entity.ImageAvatar;

import java.util.List;

@Repository
public interface ImagesAvatarRepository extends JpaRepository<ImageAvatar, String> {

    @Query("select iA.avatarUrl from ImageAvatar iA where iA.shops.shopId = ?1")
    String getShopImagesByShopId(String shopId);

    @Query("select iA.avatarUrl from ImageAvatar iA where iA.users.id = ?1")
    String getImageAvatarByUserId(String userId);

    @Modifying
    @Transactional
    @Query("UPDATE ImageAvatar iA set iA.avatarUrl = ?2 where iA.shops.shopId = ?1")
    void updateShopImage(String shopId, String imageUrl);

    @Modifying
    @Transactional
    @Query("UPDATE ImageAvatar iA set iA.avatarUrl = ?2 where iA.users.id = ?1")
    void updateAvatarImage(String userId, String imageUrl);
}
