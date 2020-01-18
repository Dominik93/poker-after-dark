package com.slusarz.pokerafterdark.spring.password;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordProvider {

    @Value("${security.administration.password}")
    private String encodedPassword;

    public String getPassword() {
        return encodedPassword;
    }

}
