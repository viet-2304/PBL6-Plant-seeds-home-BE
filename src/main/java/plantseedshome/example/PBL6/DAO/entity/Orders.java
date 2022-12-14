package plantseedshome.example.PBL6.DAO.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name= "order_id", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    private String orderId;

    @Column(name = "total")
    private int total;

    @OneToOne
    @JoinColumn(name="order_detail_id")
    private OrderDetails orderDetails;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;
}
