package com.slusarz.pokerafterdark.spring.permission;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
@AllArgsConstructor
public class RequiredAdministrationPermissionAspect {

    private PermissionChecker permissionChecker;

    @Before("execution(* *(..)) && @annotation(com.slusarz.pokerafterdark.application.permission.RequiredAdministrationPermission)")
    public Object requiredPermission(JoinPoint point) throws Throwable {
        boolean hasAdministration = permissionChecker.hasAdministrationPermission();
        if (!hasAdministration) {
            throw new SecurityException("User try execute administration command without permission.");
        }
        return point;
    }

}
