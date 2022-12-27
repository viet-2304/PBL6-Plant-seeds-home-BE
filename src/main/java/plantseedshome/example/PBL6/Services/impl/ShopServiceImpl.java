package plantseedshome.example.PBL6.Services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.entity.ImageAvatar;
import plantseedshome.example.PBL6.DAO.entity.Shops;
import plantseedshome.example.PBL6.DAO.repository.ImagesAvatarRepository;
import plantseedshome.example.PBL6.DAO.repository.ShopRepository;
import plantseedshome.example.PBL6.Services.ShopService;
import plantseedshome.example.PBL6.Services.UserService;
import plantseedshome.example.PBL6.dto.ShopDto;
import plantseedshome.example.PBL6.mapper.ShopMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final   ShopRepository shopRepository;

    private final ShopMapper shopMapper;

    private final UserService userService;

    private final ImagesAvatarRepository imagesAvatarRepository;

    @Override
    public List<ShopDto> getAllShop() {
        List<ShopDto> shopDtoList = shopRepository.findAll().stream().map(shops -> shopMapper.shopToShopDto(shops)).collect(Collectors.toList());
        shopDtoList.forEach(shopDto -> {
            shopDto.setImageUrl(imagesAvatarRepository.getShopImagesByShopId(shopDto.getShopId()));
        });
        return shopDtoList;
    }

    @Override
    public ShopDto findByUserId(String userId) {
        ShopDto shopDto =   shopMapper.shopToShopDto(shopRepository.findShopsByUserId(userId));
        String imageUrl = imagesAvatarRepository.getShopImagesByShopId(shopDto.getShopId());
        if (!imageUrl.isEmpty()) {
            shopDto.setImageUrl(imageUrl);
        }
        return shopDto;

    }

    @Override
    public List<ShopDto> findShopByShopName(String shopName) {
        return shopRepository.findShopsByShopName(shopName).get().stream().map(shops -> shopMapper.shopToShopDto(shops)).collect(Collectors.toList());
    }

    @Override
    public String addNewShop(ShopDto shopDto) {
        if (shopRepository.findShopsByUserId(shopDto.getUserId()) == null) {
        Shops shops = shopMapper.shopDtoToShop(shopDto);
        String imagesUrl = shopDto.getImageUrl();
        userService.updateUserRole(shopDto.userId, "2");
        shopRepository.save(shops);
        Shops shopAdd = shopRepository.findShopsByUserId(shopDto.getUserId());
        imagesAvatarRepository.save(new ImageAvatar("", imagesUrl, null, shopAdd));
        return "success";
        }

        return "userExit";
    }

    @Override
    public ShopDto editShop(ShopDto shopDto) {
        Shops shops = shopMapper.shopDtoToShop(shopDto);
        String imagesUrl = shopDto.getImageUrl();
        imagesAvatarRepository.updateShopImage(shopDto.getShopId(), imagesUrl);
        shopRepository.editShop(shops.getShopId(), shops.getShopName(), shops.getAddress(), shops.getPhoneNumber(),shops.getEmail());
        return shopMapper.shopToShopDto(shopRepository.findById(shopDto.getShopId()).get());
    }

    @Override
    public ShopDto getShopById(String shopId) {
        ShopDto shopDto= shopMapper.shopToShopDto(shopRepository.findById(shopId).get());
        String imagesUrl = imagesAvatarRepository.getShopImagesByShopId(shopId);
        if (!imagesUrl.isEmpty()) {
            shopDto.setImageUrl(imagesUrl);
        }
        return  shopDto;
    }
}
