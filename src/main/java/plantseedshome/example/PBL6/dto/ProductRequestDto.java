package plantseedshome.example.PBL6.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequestDto {
    private String productId;
    private String productName;
    private String description;
    private Date EXP;
    private Date MFG;
    private Date createDate;
    private String manufacturer;
    private int price;
    private int numberOfProduct;
    private List<String> imagesUrl;
    private String shops;
    private String productType;
}
