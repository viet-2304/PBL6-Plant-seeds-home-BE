package plantseedshome.example.PBL6.DAO.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment_method")
public class PaymentMethod {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name= "payment_method_id", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    private String PaymentMethodId;

    @Column(name = "payment_method_name")
    private String PaymentMethodName;

    @OneToOne(mappedBy = "paymentMethod", cascade = CascadeType.ALL)
    private Orders order;
}
