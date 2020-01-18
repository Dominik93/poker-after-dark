package com.slusarz.pokerafterdark.spring.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenTimeoutProvider {

    @Value("${security.administration.token.timeout}")
    private int timeout;

    public int getTimeout() {
        return timeout;
    }


}
