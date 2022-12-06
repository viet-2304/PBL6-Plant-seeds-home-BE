package plantseedshome.example.PBL6.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import plantseedshome.example.PBL6.DAO.entity.Carts;
import plantseedshome.example.PBL6.dto.CartDto;
import plantseedshome.example.PBL6.dto.ProductDto;

@Mapper
public interface CartMapper{

    @Mapping(source = "users.id", target = "userId")
    @Mapping(source = "products.productId", target = "productId")
    CartDto cartToCartDto(Carts carts);

    @Mapping(source = "userId", target = "users.id")
    @Mapping(source = "productId", target = "products.productId")
    @Mapping(source= "number", target= "numberOfProduct")
    Carts cartDtoToCart(CartDto cartDto);
}
