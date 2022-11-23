package plantseedshome.example.PBL6.Services;

import plantseedshome.example.PBL6.dto.ShopDto;

import java.util.List;

public interface ShopService {
    List<ShopDto> getAllShop();
    ShopDto findByUserId(String userId);
    List<ShopDto> findShopByShopName(String shopName);
    void addNewShop(ShopDto shopDto);
    void editShop(ShopDto shopDto);
}
