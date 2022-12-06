package plantseedshome.example.PBL6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAndNumberDto {
    public String numberOfProductInCart;
    public String cartId;
//    public ProductDto productDto;
    public String productId;
    public String productName;
    public String price;
    public String imageProduct;
    public String manufacturer;
    public String description;
}
