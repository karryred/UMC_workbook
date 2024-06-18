package UMC.demo.study.AOP;

import UMC.demo.study.ApiPayload.code.status.ErrorStatus;
import UMC.demo.study.ApiPayload.exception.GeneralException;
import UMC.demo.study.ApiPayload.exception.handler.PageHandler;
import UMC.demo.study.validation.annotation.CheckPage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Aspect
@Component
public class CheckPageAspect {

    @Around("execution(* *(..)) && @annotation(UMC.demo.study.validation.annotation.CheckPage)")
    public Object checkAndModifyPageParameter(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        Parameter[] parameters = method.getParameters();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(UMC.demo.study.validation.annotation.CheckPage.class)) {
                Object arg = args[i];
                if (arg instanceof Integer) {
                    Integer pageNumber = (Integer) arg;
                    if (pageNumber < 0) {
                        throw new IllegalArgumentException("Invalid page number: " + pageNumber);
                    }
                    // page - 1 연산 실행
                    args[i] = pageNumber - 1;
                }
            }
        }

        return joinPoint.proceed(args);
    }
}