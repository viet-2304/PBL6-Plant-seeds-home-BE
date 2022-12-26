package plantseedshome.example.PBL6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseWithOrderDto {
    private String productId;
    private String productName;
    private List<String> imagesUrl;
    private String shopId;
    private String shopName;
    private int number;
    private int total;
}
