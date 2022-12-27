package plantseedshome.example.PBL6.common.constant;

public class SecurityConstant {
    private SecurityConstant() {
    }

    public static final String HEADER_NAME = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String LOGIN_PATH = "/api/v1/auth/login";
    public static final String REGISTER_PATH = "/api/v1/users/createUser";
    public static final String PATH="/";
    public static final String GET_ALL_PRODUCT = "/api/v1/product/getAllProduct";
    public static final String GET_PRODUCT_BY_ID = "/api/v1/product/getProduct";
    public static final String GET_PRODUCT_WITH_TYPE = "/api/v1/product/getProductWithType";
    public static final String GET_ALL_PRODUCT_TYPE = "/api/v1/product/getAllProductType";
    public static final String GET_PRODUCT_BY_SHOP = "/api/v1/product/getProductByShop";
    public static final String GET_PRODUCT_BEST_SELLER = "/api/v1/product/getBestSeller";
    public static final String VERIFICATION_EMAIL = "/api/v1/users/verify";
    public static final String FIND_ALL_SHOP="/api/v1/shop/getAllShop";

    public static final String[] PUBLIC_MATCHERS = {LOGIN_PATH,
            VERIFICATION_EMAIL,
            REGISTER_PATH,
            GET_ALL_PRODUCT,
            PATH,
            FIND_ALL_SHOP,
            GET_PRODUCT_BY_ID,
            GET_PRODUCT_WITH_TYPE,
            GET_ALL_PRODUCT_TYPE,
            GET_PRODUCT_BY_SHOP,
            GET_PRODUCT_BEST_SELLER
    };
}
