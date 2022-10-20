package plantseedshome.example.PBL6.DAO.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {
    @Id
    @GeneratedValue
    @Column(name = "status_id")
    private String statusId;

    @Column(name = "status_name")
    private String StatusName;

    @OneToOne(mappedBy = "orderStatus", cascade = CascadeType.ALL)
    private Orders order;
}
