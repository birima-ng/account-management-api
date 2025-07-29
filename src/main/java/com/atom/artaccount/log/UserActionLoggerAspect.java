package com.atom.artaccount.log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
public class UserActionLoggerAspect {

    @Autowired
    private UserActionLogRepository userActionLogRepository;

    @Pointcut("within(@org.springframework.stereotype.Controller *) || within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerMethods() {
    }

    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logAfterMethodCall(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        if (method.isAnnotationPresent(IgnoreAudit.class)) {
            return; // Skip auditing for this method
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        UserAction userAction = method.getAnnotation(UserAction.class);
        String actionDescription = (userAction != null) ? userAction.value() : method.getName();

        UserActionLog log = new UserActionLog();
        log.setUsername(username);
        log.setAction(actionDescription);
        log.setTimestamp(LocalDateTime.now());

        userActionLogRepository.save(log);

    }
}
