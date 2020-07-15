package com.slusarz.pokerafterdark.spring.authentication;

import com.slusarz.pokerafterdark.spring.password.PasswordProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class DefaultPasswordMatcher implements PasswordMatcher {

    private PasswordEncoder passwordEncoder;

    private PasswordProvider passwordProvider;

    @Override
    public boolean matches(String password) {
        return passwordEncoder.matches(password, passwordProvider.getPassword());
    }
}
