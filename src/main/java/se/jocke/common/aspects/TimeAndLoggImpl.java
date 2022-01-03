package se.jocke.common.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import se.jocke.common.util.GenericToStringBuilder;

@Aspect
@Configuration
public class TimeAndLoggImpl extends BaseLogger{

    @Around("@annotation(se.jocke.common.aspects.annotation.TimeAndLogg)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        logMethodeAndParameters(joinPoint);
        try {
            long startTime = System.currentTimeMillis();
            Object returnValue = logResult(joinPoint.proceed());
            long timeTaken = System.currentTimeMillis() - startTime;
            logTimeSpent("Time Taken by {} is {} ms", joinPoint, timeTaken);
            return returnValue;
        } catch (Throwable throwable) {
            logException(throwable);
            throw throwable;
        }

    }



}