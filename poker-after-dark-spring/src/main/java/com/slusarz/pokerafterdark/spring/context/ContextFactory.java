package com.slusarz.pokerafterdark.spring.context;

import com.slusarz.pokerafterdark.spring.authentication.AuthenticationService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContextFactory {

    private AuthenticationService authenticationService;

    public Context createContext(String token) {
        if (authenticationService.isAuthenticated(token)) {
            return new AdministrationContext(token);
        }
        return new AnonymousContext();
    }
}
