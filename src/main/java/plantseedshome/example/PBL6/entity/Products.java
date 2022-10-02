package plantseedshome.example.PBL6.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
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
    private Collection<ImagesProduct> imagesProducts;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private Collection<Comments> comments;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private Collection<Carts> carts;
}
