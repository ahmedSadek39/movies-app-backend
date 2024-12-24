package movie.logging;

import movie.security.payload.request.LoginRequest;
import movie.security.payload.request.RegisterRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class SecurityLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(SecurityLoggingAspect.class);

    @AfterReturning(pointcut = "execution(* movie.security.AuthController.*(..))", returning = "result")
    public void logAfterReturningMethod(JoinPoint joinPoint, Object result) {
        logSensitiveMethodDetails(joinPoint, "Exiting");
        logReturnValue(result);
    }

    @AfterThrowing(pointcut = "execution(* movie.security.AuthController.*(..))", throwing = "exception")
    public void logAfterThrowingMethod(JoinPoint joinPoint, Throwable exception) {
        logSensitiveMethodDetails(joinPoint, "Exception occurred");
        logException(exception);
    }

    private void logSensitiveMethodDetails(JoinPoint joinPoint, String status) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String filteredArgs = filterSensitiveData(args);

        logger.info("{} method: {} with arguments: {}", status, methodName, filteredArgs);
    }

    private String filterSensitiveData(Object[] args) {
        if (args == null || args.length == 0) {
            return "No arguments";
        }

        return Arrays.stream(args)
                .map(arg -> {
                    if (arg instanceof LoginRequest loginRequest) {
                        return String.format("LoginRequest{username='%s', password='[HIDDEN]'}", loginRequest.getUsername());
                    }
                    if (arg instanceof RegisterRequest registerRequest) {
                        return String.format("RegisterRequest{username='%s', role='%s', password='[HIDDEN]'}",
                                registerRequest.getUsername(), registerRequest.getRole());
                    }
                    return arg != null ? arg.toString() : "null";
                })
                .toList()
                .toString();
    }

    private void logReturnValue(Object result) {
        if (result != null) {
            logger.info("Method returned: {}", result);
        } else {
            logger.info("Method returned no result");
        }
    }

    private void logException(Throwable exception) {
        logger.error("Exception: {}", exception.getMessage(), exception);
    }
}
