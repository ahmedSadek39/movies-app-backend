package movie.security.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfigurationProvider {

    @Value("${allowed.methods}")
    private String[] allowedMethods;

    @Value("${allowed.origins}")
    private String[] allowedOrigins;


    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = createCorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private CorsConfiguration createCorsConfiguration(){
        CorsConfiguration configuration = new CorsConfiguration();
        if (allowedOrigins.length == 1 && "*".equals(allowedOrigins[0])) {
            configuration.addAllowedOrigin("*");
        } else {
            for (String allowedOrigin : allowedOrigins) {
                configuration.addAllowedOrigin(allowedOrigin);
            }
        }
        for (String allowedMethod : allowedMethods) {
            configuration.addAllowedMethod(allowedMethod);
        }
        configuration.addAllowedHeader("*");
        return configuration;
    }
}