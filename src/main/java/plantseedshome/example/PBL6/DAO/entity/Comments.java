package plantseedshome.example.PBL6.DAO.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name= "comment_id", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    private String commentId;

    @Column(name = "description")
    private String description;

    @Column(name = "comment_time")
    private Date commentTime;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @OneToMany(mappedBy = "commentId", cascade = CascadeType.ALL)
    private Collection<ImagesProduct> imagesProducts;
}
