package com.rohini.aop.demoaop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    @Before("execution(* com.rohini.aop.demoaop.service.UserService.saveUser(..))")
    public void checkUserRole() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String role = ((UserDetails) principal).getAuthorities().toString();
            if (!role.contains("ADMIN")) {
                throw new SecurityException("User is not authorized to perform this action");
            }
        }
    }
}
