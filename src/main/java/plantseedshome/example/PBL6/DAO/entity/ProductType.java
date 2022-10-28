package plantseedshome.example.PBL6.DAO.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_type")
@Getter
@Setter

public class ProductType {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name= "product_type_id", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    private String ProductTypeId;

    @Column(name = "name")
    private String Name;

}
