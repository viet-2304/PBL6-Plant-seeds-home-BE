package plantseedshome.example.PBL6.DAO.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Products {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name= "product_id", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    private String productId;

    @Column(name= "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "EXP")
    private Date exp;

    @Column(name = "MFG")
    private Date MFG;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "price")
    private int price;

    @Column(name = "rating")
    private int rating;

    @Column(name = "number_of_product")
    private int numberOfProduct;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shops shops;

    @OneToOne
    @JoinColumn(name = "product_type")
    private ProductType productType;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private Collection<OrderDetails> orderDetails;


    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private Collection<Comments> comments;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private Collection<Carts> carts;
}
