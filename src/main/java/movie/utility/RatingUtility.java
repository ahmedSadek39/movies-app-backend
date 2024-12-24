package movie.utility;

import movie.model.Rating;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class RatingUtility {

    public static double normalizeRating(String rawRating) {
        if (rawRating == null || rawRating.trim().isEmpty()) {
            return -1;
        }

        try {
            rawRating = rawRating.trim();

            if (rawRating.endsWith("%")) {

                double percentage = Double.parseDouble(rawRating.replace("%", ""));
                return percentage / 10;
            } else if (rawRating.contains("/")) {

                String[] parts = rawRating.split("/");
                if (parts.length == 2) {
                    double numerator = Double.parseDouble(parts[0]);
                    double denominator = Double.parseDouble(parts[1]);
                    return (numerator / denominator) * 10;
                }
            } else {
                return Double.parseDouble(rawRating);
            }
        } catch (NumberFormatException e) {
            System.err.println("Failed to parse rating: " + rawRating);
        }

        return -1;
    }

    public static Double calculateAverageRating(List<Rating> ratings) {
        if (ratings == null || ratings.isEmpty()) {
            return null;
        }

        double average = ratings.stream()
                .map(rating -> RatingUtility.normalizeRating(rating.getValue()))
                .filter(value -> value != -1)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        return BigDecimal.valueOf(average)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
