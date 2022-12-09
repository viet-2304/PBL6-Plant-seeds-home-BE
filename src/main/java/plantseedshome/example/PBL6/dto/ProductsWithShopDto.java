package plantseedshome.example.PBL6.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductsWithShopDto {
    public String shopId;
    public String shopName;
    public List<ProductAndNumberDto> listProductAndNumberDto;
}
