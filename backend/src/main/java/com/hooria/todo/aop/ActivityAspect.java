package com.hooria.todo.aop;

import com.hooria.todo.domain.Action;
import com.hooria.todo.domain.ActivityLog;
import com.hooria.todo.domain.Status;
import com.hooria.todo.service.ActivityLogService;
import com.hooria.todo.session.SessionUser;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Component
@Aspect
@RequiredArgsConstructor
public class ActivityAspect {

    private final ActivityLogService activityLogService;

//    @Around("execution(* com.hooria.todo.service.CardService..*(..)) && !@annotation(com.hooria.todo.aop.ExcludeLogging)")
    public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        String userId = sessionUser.getUserId();
        Action action = Action.of(request.getParameter("action"));
        String taskTitle = request.getParameter("taskTitle");
        Status fromStatus = Status.of(request.getParameter("fromStatus"));
        Status toStatus = Status.of(request.getParameter("toStatus"));

        ActivityLog activityLog = ActivityLog.of(userId, action, taskTitle, fromStatus, toStatus, LocalDateTime.now(), false);
        activityLogService.add(activityLog);

        return joinPoint.proceed();
    }
}
