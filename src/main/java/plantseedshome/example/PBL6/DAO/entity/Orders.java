package plantseedshome.example.PBL6.DAO.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @GeneratedValue
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "number")
    private int number;

    @Column(name = "total")
    private String total;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @OneToOne
    @JoinColumn(name = "payment_method")
    private  PaymentMethod paymentMethod;

    @OneToOne
    @JoinColumn(name = "order_status")
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private Collection<OrderDetails> orderDetails;
}
