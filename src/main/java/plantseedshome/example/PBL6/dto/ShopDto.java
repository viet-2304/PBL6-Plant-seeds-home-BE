package plantseedshome.example.PBL6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto {
    public String shopId;
    public String shopName;
    public String address;
    public String phoneNumber;
    public String email;
    public String userId;
    public String isDelete;
    public String imageUrl;
}
