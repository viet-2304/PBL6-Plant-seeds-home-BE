package plantseedshome.example.PBL6.Services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.entity.Carts;
import plantseedshome.example.PBL6.DAO.repository.CartRepository;
import plantseedshome.example.PBL6.Services.CartService;
import plantseedshome.example.PBL6.Services.ProductService;
import plantseedshome.example.PBL6.dto.CartDto;

import plantseedshome.example.PBL6.dto.CartResponseDto;

import plantseedshome.example.PBL6.dto.ProductDto;
import plantseedshome.example.PBL6.mapper.CartMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductService productService;

    @Autowired
    CartMapper cartMapper;

    @Override
    public List<CartDto> getAllCart() {
      return  cartRepository.findAll().stream().map(carts -> cartMapper.cartToCartDto(carts)).collect(Collectors.toList());
    }

    @Override
    public CartResponseDto getCartWithId(String id) {
        Carts carts =  cartRepository.findById(id).get();
        ProductDto productDto = productService.findProductById(carts.getProducts().getProductId());
        CartDto cartDto = cartMapper.cartToCartDto(carts);
        CartResponseDto cartResponseDto = new CartResponseDto(cartDto,productDto);

        return cartResponseDto;
    }

    @Override
    public List<CartDto> getCartWithUserId(String userId) {
    return  cartRepository.findByUserId(userId).get().stream().map(carts -> cartMapper.cartToCartDto(carts)).collect(Collectors.toList());
    }

    @Override
    public void addProductToCart(CartDto cartDto) {
        cartRepository.save(cartMapper.cartDtoToCart(cartDto));
    }

    @Override
    public void updateProductInCart(CartDto cartDto) {
        cartRepository.updateProductInCart(cartDto.number, cartDto.id);
    }

    @Override
    public void deleteProductInCart(String id) {
        cartRepository.deleteById(id);
    }
}
