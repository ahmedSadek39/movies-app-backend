package movie.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import movie.model.Movie;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecification {

    public static Specification<Movie> withFilters(String title, String year, String type) {
        return (Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (title != null && !title.trim().isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }

            if (year != null && !year.trim().isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(criteriaBuilder.lower(root.get("year")), year.toLowerCase()));
            }

            if (type != null && !type.trim().isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(criteriaBuilder.lower(root.get("type")), type.toLowerCase()));
            }
//
//            if (imdbID != null && !imdbID.trim().isEmpty()) {
//                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(criteriaBuilder.lower(root.get("imdbID")), imdbID.toLowerCase()));
//            }

            return predicate;
        };
    }
}
