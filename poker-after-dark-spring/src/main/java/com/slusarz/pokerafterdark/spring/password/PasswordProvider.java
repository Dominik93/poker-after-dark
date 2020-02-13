package com.slusarz.pokerafterdark.spring.password;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordProvider {

    @Value("${poker-after-dark.administration.password}")
    private String encodedPassword;

    public String getPassword() {
        return encodedPassword;
    }

}
