package se.jensen.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import se.jensen.util.GenericToStringBuilder;

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
            LOGGER.info("Time Taken by {} is {} ms", joinPoint, timeTaken);
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
        CodeSignature methodSignature = (CodeSignature) joinPoint.getSignature();
        String[] sigParamNames = methodSignature.getParameterNames();
        Object[] params = joinPoint.getArgs();

        LOGGER.info(joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName());
        for (int i = 0; i < params.length; ++i) {
            LOGGER.info(String.format("param %s = %s",sigParamNames[i],params[i]));
        }

    }

    private Object logResult(Object proceed) {
        LOGGER.info("Return Value: " + GenericToStringBuilder.toString(proceed));
        return proceed;
    }
}