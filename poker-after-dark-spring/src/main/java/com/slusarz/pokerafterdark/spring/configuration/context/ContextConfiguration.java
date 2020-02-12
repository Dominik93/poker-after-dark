package com.slusarz.pokerafterdark.spring.configuration.context;

import com.slusarz.pokerafterdark.spring.authentication.AuthenticationService;
import com.slusarz.pokerafterdark.spring.context.ContextFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfiguration {

    @Bean
    public ContextFactory contextFactory(AuthenticationService authenticationService) {
        return new ContextFactory(authenticationService);

    }

}
