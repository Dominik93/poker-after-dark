package com.slusarz.pokerafterdark.spring.configuration;

import com.slusarz.pokerafterdark.spring.token.TokenGenerator;
import com.slusarz.pokerafterdark.spring.token.TokenTimeoutProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenConfiguration {

    @Bean
    public TokenGenerator tokenGenerator(TokenTimeoutProvider tokenTimeoutProvider) {
        return new TokenGenerator(tokenTimeoutProvider);
    }

}
