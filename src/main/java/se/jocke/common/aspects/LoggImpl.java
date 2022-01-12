package se.jocke.common.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggImpl extends BaseLogger {

    @Around("@annotation(se.jocke.common.aspects.annotation.Logg)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = String.format("%s.%s", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());
        MDC.put("api-method", method);
        logMethodeAndParameters(joinPoint);
        try {
            Object returnValue = logResult(joinPoint.proceed());
            return returnValue;
        } catch (Throwable throwable) {
            logException(throwable);
            throw throwable;
        }

    }
}
