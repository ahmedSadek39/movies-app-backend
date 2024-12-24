package movie.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class GeneralLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(GeneralLoggingAspect.class);

    @Before("execution(* movie.controller.*.*(..))")
    public void logBeforeMethods(JoinPoint joinPoint) {
        logMethodDetails(joinPoint, "Entering");
    }

    @AfterReturning(pointcut = "execution(* movie.controller.*.*(..))", returning = "result")
    public void logAfterReturningMethod(JoinPoint joinPoint, Object result) {
        logMethodDetails(joinPoint, "Exiting");
        logReturnValue(result);
    }

    @AfterThrowing(pointcut = "execution(* movie.controller.*.*(..))", throwing = "exception")
    public void logAfterThrowingMethod(JoinPoint joinPoint, Throwable exception) {
        logMethodDetails(joinPoint, "Exception occurred");
        logException(exception);
    }

    private void logMethodDetails(JoinPoint joinPoint, String status) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String argsString = args != null && args.length > 0
                ? "Arguments: " + Arrays.toString(args)
                : "No arguments";

        logger.info("{} method: {} with {}", status, methodName, argsString);
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
