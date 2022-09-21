package plantseedshome.example.PBL6.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "user_id")
    private String userId;

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

    @Column(name= "role_id")
    private String roleId;
}
