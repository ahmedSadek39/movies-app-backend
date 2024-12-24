package movie.utility;

import java.util.List;

public class ValidationUtility {

    public static boolean isValidString(Object result) {
        return !(result instanceof String) || ((String) result).isEmpty();
    }

    public static boolean isNonEmptyArray(List<String> result) {
        return result == null || result.isEmpty();
    }

}
