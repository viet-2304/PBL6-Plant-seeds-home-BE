package plantseedshome.example.PBL6.Services;

import plantseedshome.example.PBL6.dto.CartDto;
import plantseedshome.example.PBL6.dto.CartResponseDto;
import plantseedshome.example.PBL6.dto.ProductAndNumberDto;
import plantseedshome.example.PBL6.dto.ProductResponseWithUserIdDto;

import java.util.List;

public interface CartService {
    List<ProductAndNumberDto> getAllCart();
    CartResponseDto getCartWithId(String id);
    ProductResponseWithUserIdDto getCartWithUserId(String userId);
    CartDto findCartDtoByCartId(String id);
    void addProductToCart(CartDto cartDto);
    void updateProductInCart(CartDto cartDto);
    void deleteProductInCart(String id);
}
