package plantseedshome.example.PBL6.DAO.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name= "user_id", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    private String id;

    @Column(name= "email", nullable = false)
    private String email;

    @Column(name= "user_name")
    private String userName;

    @Column(name= "password")
    private String password;
    @Column(name= "phone_number")
    private String phoneNumber;

    @Column(name= "address")
    private String address;

    @ManyToOne()
    @JoinColumn(name= "role_id")
    private Roles roles;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private  Collection<Comments> comments;



}
