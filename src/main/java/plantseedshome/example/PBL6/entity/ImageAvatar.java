package plantseedshome.example.PBL6.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.persistence.*;

@Entity
@Table(name = "images_avatar")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageAvatar {
    @Id
    @GeneratedValue
    @Column(name = "avatar_id")
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
