package movie;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"movie"})
@EntityScan("movie.*")
@EnableJpaRepositories({"movie.security.repository", "movie.repository"})
@OpenAPIDefinition(info = @Info(title = "Swagger UI"))
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
