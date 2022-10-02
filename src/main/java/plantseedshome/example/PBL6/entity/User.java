package plantseedshome.example.PBL6.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(name= "user_id")
    private String id;

    @Column(name= "email")
    private String email;

    @Column(name= "phone_number")
    private String phoneNumber;

    @Column(name= "address")
    private String address;

    @Column(name= "user_name")
    private String userName;

    @Column(name= "password")
    private String password;

    @OneToOne
    @JoinColumn(name= "role_id")
    private Roles roles;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private ImageAvatar imageAvatar;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private Shops shops;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private  Collection<Comments> comments;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Collection<OrderDetails> orderDetails;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Collection<Carts>carts;
}
