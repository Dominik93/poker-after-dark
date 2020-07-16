package com.slusarz.pokerafterdark.spring.configuration.security;

import com.slusarz.pokerafterdark.spring.authentication.AuthenticationService;
import com.slusarz.pokerafterdark.spring.authentication.DefaultPasswordMatcher;
import com.slusarz.pokerafterdark.spring.authentication.PasswordMatcher;
import com.slusarz.pokerafterdark.spring.common.permission.AdministrationPermissionChecker;
import com.slusarz.pokerafterdark.spring.common.permission.PermissionChecker;
import com.slusarz.pokerafterdark.spring.common.permission.RequiredAdministrationPermissionAspect;
import com.slusarz.pokerafterdark.spring.password.PasswordProvider;
import com.slusarz.pokerafterdark.spring.token.TokenGenerator;
import com.slusarz.pokerafterdark.spring.token.TokenTimeoutProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PermissionChecker permissionChecker() {
        return new AdministrationPermissionChecker();
    }

    @Bean
    public DefaultPasswordMatcher defaultPasswordMatcher(PasswordProvider passwordProvider,
                                                         PasswordEncoder passwordEncoder) {
        return new DefaultPasswordMatcher(passwordEncoder, passwordProvider);
    }

    @Bean
    public RequiredAdministrationPermissionAspect requiredAdministrationPermissionAspect(PermissionChecker permissionChecker) {
        return new RequiredAdministrationPermissionAspect(permissionChecker);
    }

    @Bean
    public AuthenticationService authenticationService(TokenGenerator tokenGenerator,
                                                       PasswordMatcher passwordMatcher,
                                                       TokenTimeoutProvider tokenTimeoutProvider) {
        return new AuthenticationService(tokenGenerator, tokenTimeoutProvider, passwordMatcher);
    }
}
