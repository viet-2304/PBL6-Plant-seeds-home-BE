package plantseedshome.example.PBL6.Services;

import plantseedshome.example.PBL6.dto.CartDto;

import java.util.List;

public interface CartService {
    List<CartDto> getAllCart();
    CartDto getCartWithId(String id);
//    List<CartDto> getCartWithUserId(String userId);
//    void createCart(CartDto cartDto);
//    void deleteProductInCart(CartDto cartDto);
//    void addProductToCart(CartDto cartDto);
//    void updateProductInCart(CartDto cartDto);

}
