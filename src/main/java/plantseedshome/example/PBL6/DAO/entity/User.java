package plantseedshome.example.PBL6.DAO.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    @Column(name= "user_id")
    private String id;

    @Column(name= "email", nullable = false)
    private String email;

    @Column(name= "phone_number")
    private String phoneNumber;

    @Column(name= "address")
    private String address;

    @Column(name= "user_name")
    private String userName;

    @Column(name= "password")
    private String password;

    @ManyToOne()
    @JoinColumn(name= "role_id")
    private Roles roles;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private  Collection<Comments> comments;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Collection<OrderDetails> orderDetails;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Collection<Carts>carts;
}
