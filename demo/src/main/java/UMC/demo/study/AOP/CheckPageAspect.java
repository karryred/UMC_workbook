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

    @Around("execution(* *(.., @UMC.demo.study.validation.annotation.CheckPage (*), ..)) && args(.., pageNumber)")
    public Object checkAndModifyPageParameter(ProceedingJoinPoint joinPoint, Integer pageNumber) throws Throwable {

        if (pageNumber == null) {
            return joinPoint.proceed();
        }

        if (pageNumber < 1) {
            throw new IllegalArgumentException("Invalid page number: " + pageNumber);
        }

        // pageNumber를 -1 연산하여 args 배열에 다시 반영
        Object[] args = joinPoint.getArgs();
        args[args.length-1]=pageNumber-1;

        return joinPoint.proceed(args);
    }
}