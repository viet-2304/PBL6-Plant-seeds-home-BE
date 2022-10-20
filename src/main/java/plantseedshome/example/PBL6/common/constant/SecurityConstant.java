package plantseedshome.example.PBL6.common.constant;

public class SecurityConstant {
    private SecurityConstant() {
    }

    public static final String HEADER_NAME = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String LOGIN_PATH = "/api/v1/auth/login";

    public static final String REGISTER_PATH = "/api/v1/users/createUser";
//    public static final String RESOURCE_PATH = "/resources/**";
    public static final String VERIFICATION_EMAIL = "/api/v1/users/verify";
    public static final String[] PUBLIC_MATCHERS = {LOGIN_PATH, VERIFICATION_EMAIL, REGISTER_PATH};
}
