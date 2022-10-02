package plantseedshome.example.PBL6.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment_method")
public class PaymentMethod {
    @Id
    @GeneratedValue
    @Column(name = "payment_method_id")
    private String PaymentMethodId;

    @Column(name = "payment_method_name")
    private String PaymentMethodName;

    @OneToOne(mappedBy = "paymentMethod", cascade = CascadeType.ALL)
    private Orders order;
}
