package movie.repository;

import movie.dto.MovieDetailsDto;
import movie.payload.request.MovieRequestDto;
import movie.payload.response.MovieResponseDto;
import movie.security.auth.SecurityConstants;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class MovieOmRepository {

    private final WebClient webClient ;

    public MovieOmRepository(WebClient webClient) {
        this.webClient = webClient;
    }

    public MovieResponseDto getMovies(MovieRequestDto req) {
//        System.out.println("SAd");
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/")
                        .queryParam("s", req.getKeyword())
                        .queryParam("page", req.getPage())
                        .queryParam("apiKey", SecurityConstants.API_KEY)
                        .build())
                .retrieve()
                .bodyToMono(MovieResponseDto.class).block();
    }

    public MovieDetailsDto getMovieDetailsById(String imdbID) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/")
                        .queryParam("i", imdbID)
                        .queryParam("apiKey", SecurityConstants.API_KEY)
                        .build())
                .retrieve()
                .bodyToMono(MovieDetailsDto.class).block();
    }
}
