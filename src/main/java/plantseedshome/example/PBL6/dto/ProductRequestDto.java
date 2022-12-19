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
    private String productName;
    private String description;

    private Date EXP;
    private Date MFG;
    private String manufacturer;
    private int price;
    private int rating;
    private int numberOfProduct;
//    private List<String> imageURL;
    private MultipartFile[] files;
    private String shops;
    private String productType;
}
