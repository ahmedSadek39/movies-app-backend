package movie.exception.handler.types;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String usernameAlreadyExist) {
        super(usernameAlreadyExist);
    }
}
