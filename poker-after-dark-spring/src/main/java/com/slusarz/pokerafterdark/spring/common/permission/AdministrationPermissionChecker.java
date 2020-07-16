package com.slusarz.pokerafterdark.spring.common.permission;

import com.slusarz.pokerafterdark.spring.context.ContextHolder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AdministrationPermissionChecker implements PermissionChecker {

    public boolean hasAdministrationPermission() {
        return ContextHolder.getContext().isAdministrationMode();
    }

}
