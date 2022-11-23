package plantseedshome.example.PBL6.Services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.entity.Shops;
import plantseedshome.example.PBL6.DAO.repository.ShopRepository;
import plantseedshome.example.PBL6.Services.ShopService;
import plantseedshome.example.PBL6.dto.ShopDto;
import plantseedshome.example.PBL6.mapper.ShopMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final   ShopRepository shopRepository;

    private final ShopMapper shopMapper;

    @Override
    public List<ShopDto> getAllShop() {
       return shopRepository.findAll().stream().map(shops -> shopMapper.shopToShopDto(shops)).collect(Collectors.toList());
    }

    @Override
    public ShopDto findByUserId(String userId) {
        return shopMapper.shopToShopDto(shopRepository.findShopsByUserId(userId));
    }

    @Override
    public List<ShopDto> findShopByShopName(String shopName) {
        return shopRepository.findShopsByShopName(shopName).get().stream().map(shops -> shopMapper.shopToShopDto(shops)).collect(Collectors.toList());
    }

    @Override
    public void addNewShop(ShopDto shopDto) {
        Shops shops = shopMapper.shopDtoToShop(shopDto);
        shopRepository.save(shops);
    }
}
