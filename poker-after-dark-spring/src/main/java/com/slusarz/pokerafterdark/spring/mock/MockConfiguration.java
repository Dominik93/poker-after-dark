package com.slusarz.pokerafterdark.spring.mock;

import com.slusarz.pokerafterdark.spring.authentication.PasswordMatcher;
import com.slusarz.pokerafterdark.spring.common.permission.PermissionChecker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("MOCK_ADMINISTRATION")
public class MockConfiguration {

    @Bean
    @Primary
    public PermissionChecker mockPermissionChecker() {
        return () -> true;
    }

    @Bean
    @Primary
    public PasswordMatcher passwordMatcher() {
        return password -> true;
    }

}
