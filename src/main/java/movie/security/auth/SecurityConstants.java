package movie.security.auth;

public class SecurityConstants {

    public static final long JWT_EXPIRATION = 3600000;
    public static final String JWT_SECERT = "pokemonsecretrandomstringwithmorethan256bits";

    public final static String API_KEY = "c5b1baef";

    public static final String AUTHORIZE_ADMIN = "hasRole('ROLE_ADMIN')";
    public static final String AUTHORIZE_ADMIN_OR_USER = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')";

}