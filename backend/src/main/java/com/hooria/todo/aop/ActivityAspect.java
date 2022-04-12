package com.hooria.todo.aop;

import com.hooria.todo.service.ActivityLogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class ActivityAspect {

    private final ActivityLogService activityLogService;

    @Around("execution(* com.hooria.todo.service.CardService..*(..)) && !@target(com.hooria.todo.aop.ExcludeLogging)")
    public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable {
//        EnableLogging enableLogging = (EnableLogging) joinPoint.proceed();
//        activityLogService.add(enableLogging.toLogging());
        return joinPoint.proceed();
    }
}
