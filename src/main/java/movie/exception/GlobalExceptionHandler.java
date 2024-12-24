package movie.exception;

import movie.exception.handler.types.*;
import movie.security.payload.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static movie.utility.ResponseBuilder.buildResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    /* Global Exceptions Handler */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException ex) {
        return buildResponse(500, "Runtime Exception", ex.getMessage(), ex);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse> handleNullPointerException(NullPointerException ex) {
        return buildResponse(500, "Null Pointer Exception", ex.getMessage(), ex);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildResponse(500, "Illegal Argument", ex.getMessage(), ex);
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ApiResponse> handleSecurityException(SecurityException ex) {
        return buildResponse(401, "Unauthorized", "Unauthorized", ex);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException ex) {
        return buildResponse(403, "Access Denied", ex.getMessage(), ex);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleDateValidationException(BadRequestException ex) {
        return buildResponse(400, ex.getMessage(), null, ex);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<?> handleMovieNotFoundException(MovieNotFoundException ex) {
        return buildResponse(400, ex.getMessage(), null, ex);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return buildResponse(400, ex.getMessage(), null, ex);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return buildResponse(400, ex.getMessage(), null, ex);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex) {
        return buildResponse(400, ex.getMessage(), null, ex);
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<?> handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException ex) {
        return buildResponse(401, ex.getMessage(), null, ex);
    }

}
