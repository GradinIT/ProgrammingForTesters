package se.jensen.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import se.jensen.util.GenericToStringBuilder;

import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Aspect
@Configuration
public class TimeAndLoggImpl {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(se.jensen.aspects.TimeAndLogg)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        logMethodeAndParameters(joinPoint);
        try {
            long startTime = System.currentTimeMillis();
            Object returnValue = logResult(joinPoint.proceed());
            long timeTaken = System.currentTimeMillis() - startTime;
            LOGGER.info("Time Taken by {} is {}", joinPoint, timeTaken);
            return returnValue;
        } catch (Throwable throwable) {
            logException(throwable);
            throw throwable;
        }

    }

    private void logException(Throwable throwable) {
        LOGGER.info("Exception: " + GenericToStringBuilder.toString(throwable));
    }

    private void logMethodeAndParameters(ProceedingJoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName());
        LOGGER.info("argument:" + GenericToStringBuilder.toString(ofNullable(joinPoint.getArgs())
                .stream()
                .collect(Collectors.toList())));

    }

    private Object logResult(Object proceed) {
        LOGGER.info("Return Value: " + GenericToStringBuilder.toString(proceed));
        return proceed;
    }
}