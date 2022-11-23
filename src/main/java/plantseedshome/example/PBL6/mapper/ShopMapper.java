package plantseedshome.example.PBL6.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import plantseedshome.example.PBL6.DAO.entity.Shops;
import plantseedshome.example.PBL6.dto.ShopDto;

@Mapper
public interface ShopMapper {

    @Mapping(source = "users.id", target = "userId")
    @Mapping(source = "shopName", target = "shopName")
    @Mapping(source = "shopId", target = "shopId")
    ShopDto shopToShopDto(Shops shops);


    @Mapping(source = "userId", target = "users.id")
    @Mapping(source = "shopName", target = "shopName")
    @Mapping(source = "shopId", target = "shopId")
    Shops shopDtoToShop(ShopDto shopDto);
}
