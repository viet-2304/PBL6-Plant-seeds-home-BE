package plantseedshome.example.PBL6.Services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.entity.Carts;
import plantseedshome.example.PBL6.DAO.repository.CartRepository;
import plantseedshome.example.PBL6.Services.CartService;
import plantseedshome.example.PBL6.Services.ProductService;
import plantseedshome.example.PBL6.dto.*;

import plantseedshome.example.PBL6.mapper.CartMapper;

import java.util.ArrayList;
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
    public List<ProductAndNumberDto> getAllCart() {
        List<ProductAndNumberDto> cartResponseDtos = new ArrayList<>();
      List<CartDto> cartDtos = cartRepository.findAll().stream().map(carts -> cartMapper.cartToCartDto(carts)).collect(Collectors.toList());
      cartDtos.forEach(cartDto -> {
          ProductDto productDto = productService.findProductById(cartDto.productId);
          cartResponseDtos.add(
                  new ProductAndNumberDto(
                          cartDto.number,
                          cartDto.id,
                          productDto.getProductId(),
                          productDto.getProductName(),
                          productDto.getPrice() + "",
                          productDto.getImageURL() + "",
                          productDto.getManufacturer(),
                          productDto.getDescription()));
      });
      return cartResponseDtos;
    }

    @Override
    public CartResponseDto getCartWithId(String id) {
        Carts carts =  cartRepository.findById(id).get();
        CartDto cartDto = cartMapper.cartToCartDto(carts);
        CartResponseDto cartResponseDto = new CartResponseDto(cartDto.id, cartDto.number,productService.findProductById(carts.getProducts().getProductId()));

        return cartResponseDto;
    }

    @Override
    public ProductResponseWithUserIdDto getCartWithUserId(String userId) {
        List<ProductAndNumberDto> productAndNumberDtoList = new ArrayList<>();
    List<CartDto> listCartDto =  cartRepository.findByUserId(userId).get().stream().map(carts -> cartMapper.cartToCartDto(carts)).collect(Collectors.toList());
    if (listCartDto.isEmpty()) {
        return null;
    }
    listCartDto.forEach(cartDto -> {
        ProductDto productDto = productService.findProductById(cartDto.productId);

        productAndNumberDtoList.add(
                new ProductAndNumberDto(cartDto.number, cartDto.id ,productDto.getProductId(),
                        productDto.getProductName(),
                        productDto.getPrice() + "",
                        productDto.getImageURL() + "",
                        productDto.getManufacturer(),
                        productDto.getDescription())
                );
    });
    return new ProductResponseWithUserIdDto(userId, productAndNumberDtoList);
    }

    @Override
    public void addProductToCart(CartDto cartDto) {
        if(cartRepository.findByUserAndProduct(cartDto.userId, cartDto.productId).isPresent()) {
            Carts carts = cartRepository.findByUserAndProduct(cartDto.userId, cartDto.productId).get();
            cartRepository.updateProductInCart(
                    (Integer.parseInt(cartDto.number) +
                            Integer.parseInt(carts.getNumberOfProduct())) + "", carts.getId());
        }
        else {
            cartRepository.save(cartMapper.cartDtoToCart(cartDto));
        }
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
