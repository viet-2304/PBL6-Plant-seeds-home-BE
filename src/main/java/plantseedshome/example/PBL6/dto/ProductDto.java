package plantseedshome.example.PBL6.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private String productId;
    private String productName;
    private String description;
    private Date exp;
    private Date MFG;
    private String manufacturer;
    private int price;
    private int rating;
    private int numberOfProduct;
    private String shops;
    private String productType;
}