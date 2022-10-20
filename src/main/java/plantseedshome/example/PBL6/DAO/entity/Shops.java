package plantseedshome.example.PBL6.DAO.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "shops")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shops {
    @Id
    @GeneratedValue
    @Column(name = "shop_id")
    private String shopId;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "facebook_address")
    private String facebook;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User users;

    @OneToOne(mappedBy = "shops", cascade = CascadeType.ALL)
    private ImageAvatar imageAvatar;

    @OneToMany(mappedBy = "shops", cascade = CascadeType.ALL)
    private Collection<Products> products;
}
