package plantseedshome.example.PBL6.Services;

import plantseedshome.example.PBL6.dto.CartDto;
import plantseedshome.example.PBL6.dto.CartResponseDto;

import java.util.List;

public interface CartService {
    List<CartDto> getAllCart();
    CartResponseDto getCartWithId(String id);
    List<CartDto> getCartWithUserId(String userId);
    void addProductToCart(CartDto cartDto);
    void updateProductInCart(CartDto cartDto);
    void deleteProductInCart(String id);
}
