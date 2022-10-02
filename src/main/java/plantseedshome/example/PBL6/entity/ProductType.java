package plantseedshome.example.PBL6.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_type")
public class ProductType {

    @Id
    @GeneratedValue
    @Column(name = "product_type_id")
    private String ProductTypeId;

    @Column(name = "name")
    private String Name;

    @OneToOne(mappedBy = "productType", cascade = CascadeType.ALL)
    private Products products;
}
