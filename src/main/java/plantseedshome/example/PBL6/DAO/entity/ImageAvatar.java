package plantseedshome.example.PBL6.DAO.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "images_avatar")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageAvatar {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name= "avatar_id", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    private String avatarId;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User users;

    @OneToOne
    @JoinColumn(name = "shop_id")
    private Shops shops;
}
