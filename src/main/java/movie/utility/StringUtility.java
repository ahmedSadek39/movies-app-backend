package movie.utility;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StringUtility {

    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

}
