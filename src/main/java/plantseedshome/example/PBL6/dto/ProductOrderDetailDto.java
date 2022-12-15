package plantseedshome.example.PBL6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDetailDto {
    private String id;
    private int number;
    private int totalOfProduct;
    private String orderDetailId;
    private String productId;
}
