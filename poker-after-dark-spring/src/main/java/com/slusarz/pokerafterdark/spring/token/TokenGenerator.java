package com.slusarz.pokerafterdark.spring.token;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Random;

@AllArgsConstructor
public class TokenGenerator {

    private TokenTimeoutProvider tokenTimeoutProvider;

    public Token generate() {
        LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(tokenTimeoutProvider.getTimeout());
        return new Token(generateTokenValue(), expirationDate);
    }

    private String generateTokenValue() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
