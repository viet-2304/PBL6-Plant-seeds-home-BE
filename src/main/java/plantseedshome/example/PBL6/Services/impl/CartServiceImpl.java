package plantseedshome.example.PBL6.Services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plantseedshome.example.PBL6.DAO.entity.Carts;
import plantseedshome.example.PBL6.DAO.entity.Products;
import plantseedshome.example.PBL6.DAO.repository.CartRepository;
import plantseedshome.example.PBL6.DAO.repository.ImagesProductRepository;
import plantseedshome.example.PBL6.DAO.repository.ProductRepository;
import plantseedshome.example.PBL6.DAO.repository.ShopRepository;
import plantseedshome.example.PBL6.Services.CartService;
import plantseedshome.example.PBL6.Services.ProductService;
import plantseedshome.example.PBL6.dto.*;

import plantseedshome.example.PBL6.mapper.CartMapper;
import plantseedshome.example.PBL6.mapper.ProductMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductService productService;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImagesProductRepository imagesProductRepository;

    @Autowired
    ShopRepository shopRepository;

    @Override
    public List<ProductAndNumberDto> getAllCart() {
      List<CartDto> cartDtos = cartRepository.findAll().stream().map(carts -> cartMapper.cartToCartDto(carts)).collect(Collectors.toList());
        List<ProductAndNumberDto> productAndNumberDtoList = getProductAndNumberForCartDto(cartDtos);

        return productAndNumberDtoList;
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
        List<CartDto> listCartDto =  cartRepository.findByUserId(userId).get().stream().map(carts -> cartMapper.cartToCartDto(carts)).collect(Collectors.toList());
        if (listCartDto.isEmpty()) {
            return null;
        }
        List<ProductAndNumberDto> productAndNumberDtoList = getProductAndNumberForCartDto(listCartDto);
        List<ProductsWithShopDto> productsWithShopDtos = new ArrayList<>();
        Map<String,List<ProductAndNumberDto> > map = productAndNumberDtoList.stream().collect(Collectors.groupingBy(p -> p.shopId, Collectors.mapping(p-> p,Collectors.toList())));

        map.forEach((key,value) -> {
            ProductsWithShopDto products = new ProductsWithShopDto();
            products.shopId = key;
            products.shopName = value.get(0).getShopName();
            products.listProductAndNumberDto = value;
            productsWithShopDtos.add(products);
        });
        return new ProductResponseWithUserIdDto(userId, productsWithShopDtos);
    }

    @Override
    public String addProductToCart(CartDto cartDto) {
        if(productService.findProductById(cartDto.getProductId()) == null) {
            return "notFound";
        }
        if(productService.findProductById(cartDto.getProductId()).getNumberOfProduct() < Integer.parseInt(cartDto.number)) {
            return "outOffNumber";
        }
        if(cartRepository.findByUserAndProduct(cartDto.userId, cartDto.productId).isPresent()) {
            Carts carts = cartRepository.findByUserAndProduct(cartDto.userId, cartDto.productId).get();
            cartRepository.updateProductInCart(
                    (Integer.parseInt(cartDto.number) +
                            Integer.parseInt(carts.getNumberOfProduct())) + "", carts.getId());
        }
        else {
            cartRepository.save(cartMapper.cartDtoToCart(cartDto));
        }
        return null;
    }

    @Override
    public void updateProductInCart(CartDto cartDto) {
        cartRepository.updateProductInCart(cartDto.number, cartDto.id);
    }

    @Override
    public void deleteProductInCart(String id) {
        cartRepository.deleteById(id);
    }

    public CartDto findCartDtoByCartId(String id) {
        Carts carts =  cartRepository.findById(id).get();
        return cartMapper.cartToCartDto(carts);
    }

    private List<ProductAndNumberDto> getProductAndNumberForCartDto(List<CartDto> cartDtoList) {
        List<ProductAndNumberDto> productAndNumberDtoList = new ArrayList<>();
        cartDtoList.forEach(cartDto -> {
            Products products = productRepository.findById(
                    cartDto.getProductId()).get();
            productAndNumberDtoList.add(
                    new ProductAndNumberDto(
                            cartDto.number,
                            cartDto.id,
                            products.getProductId(),
                            products.getProductName(),
                            products.getPrice() + "",
                            imagesProductRepository.findImagesProductByProductId(products.getProductId()) +"",
                            products.getShops().getShopId(),
                            products.getShops().getShopName()
                    )
            );
        });
        return productAndNumberDtoList;
    }
}
