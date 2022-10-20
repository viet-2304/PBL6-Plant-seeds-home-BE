package plantseedshome.example.PBL6.DAO.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "order_status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name= "status_id", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    private String statusId;

    @Column(name = "status_name")
    private String StatusName;

    @OneToOne(mappedBy = "orderStatus", cascade = CascadeType.ALL)
    private Orders order;
}
