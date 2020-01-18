package com.slusarz.pokerafterdark.spring.configuration;

import com.slusarz.pokerafterdark.spring.authentication.AuthenticationService;
import com.slusarz.pokerafterdark.spring.context.ContextFactory;
import com.slusarz.pokerafterdark.spring.password.PasswordProvider;
import com.slusarz.pokerafterdark.spring.permission.AdministrationPermissionChecker;
import com.slusarz.pokerafterdark.spring.permission.PermissionChecker;
import com.slusarz.pokerafterdark.spring.permission.RequiredAdministrationPermissionAspect;
import com.slusarz.pokerafterdark.spring.token.TokenGenerator;
import com.slusarz.pokerafterdark.spring.token.TokenTimeoutProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ContextConfiguration {

    @Bean
    public ContextFactory contextFactory(AuthenticationService authenticationService) {
        return new ContextFactory(authenticationService);

    }

}
