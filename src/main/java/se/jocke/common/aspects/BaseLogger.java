package se.jocke.common.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.jocke.common.util.GenericToStringBuilder;

public class BaseLogger {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    void logException(Throwable throwable) {
        LOGGER.info("Exception: " + GenericToStringBuilder.toString(throwable));
    }

    void logMethodeAndParameters(ProceedingJoinPoint joinPoint) {
        CodeSignature methodSignature = (CodeSignature) joinPoint.getSignature();
        String[] sigParamNames = methodSignature.getParameterNames();
        Object[] params = joinPoint.getArgs();

        LOGGER.info(joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName());
        for (int i = 0; i < params.length; ++i) {
            LOGGER.info(String.format("param %s = %s", sigParamNames[i], params[i]));
        }
    }

    Object logResult(Object proceed) {
        LOGGER.info("Return Value: " + GenericToStringBuilder.toString(proceed));
        return proceed;
    }

    void logTimeSpent(String s, ProceedingJoinPoint joinPoint, long timeTaken) {
        LOGGER.info(s,joinPoint,timeTaken);
    }
}
