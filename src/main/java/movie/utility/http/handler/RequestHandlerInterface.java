package movie.utility.http.handler;

@FunctionalInterface
public interface RequestHandlerInterface<T> {
    T process() throws Exception;
}