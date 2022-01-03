package se.jocke.common.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class TimeImpl extends BaseLogger{
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(se.jocke.common.aspects.annotation.Time)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        logTimeSpent("Time taken by {} is {} ms", joinPoint, timeTaken);
        return returnValue;
    }
}
