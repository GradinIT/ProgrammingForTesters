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
public class LoggImpl extends BaseLogger{

    @Around("@annotation(se.jocke.common.aspects.annotation.Logg)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
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
