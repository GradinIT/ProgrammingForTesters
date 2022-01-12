package se.jocke.common.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Aspect
@Configuration
public class TimeImpl extends BaseLogger{
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(se.jocke.common.aspects.annotation.Time)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MDC.put("requestId", UUID.randomUUID().toString());
        long startTime = System.currentTimeMillis();
        Thread t = Thread.currentThread();
        Object returnValue = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        logTimeSpent("Time taken by {} is {} ms", joinPoint, timeTaken);
        return returnValue;
    }
}
