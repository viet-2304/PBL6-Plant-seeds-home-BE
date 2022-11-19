package plantseedshome.example.PBL6.Services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.repository.CartRepository;
import plantseedshome.example.PBL6.Services.CartService;
import plantseedshome.example.PBL6.dto.CartDto;
import plantseedshome.example.PBL6.mapper.CartMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartMapper cartMapper;

    @Override
    public List<CartDto> getAllCart() {
      return  cartRepository.findAll().stream().map(carts -> cartMapper.cartToCartDto(carts)).collect(Collectors.toList());
    }

    @Override
    public CartDto getCartWithId(String id) {
        return cartMapper.cartToCartDto(cartRepository.findById(id).get());
    }

    //    @Override
//    public void createCart(CartDto cartDto) {
//        cartRepository.save(cartMapper.cartDtoToCart(cartDto));
//    }

//    @Override
//    public List<CartDto> getCartWithUserId(String userId) {
//        return null;
//    }
//
//    @Override
//    public void deleteProductInCart(CartDto cartDto) {
//    }
//
//    @Override
//    public void addProductToCart(CartDto cartDto) {
//
//    }
//
//    @Override
//    public void updateProductInCart(CartDto cartDto) {
//
//    }
}
