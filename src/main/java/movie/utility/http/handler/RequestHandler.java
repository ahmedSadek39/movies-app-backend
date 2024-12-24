package movie.utility.http.handler;

public class RequestHandler {

    public static <T> T handleRequest(RequestHandlerInterface<T> handler) {
        try {
            return handler.process();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}