package com.slusarz.pokerafterdark.spring.authentication;

import com.slusarz.pokerafterdark.spring.password.PasswordProvider;
import com.slusarz.pokerafterdark.spring.token.Token;
import com.slusarz.pokerafterdark.spring.token.TokenGenerator;
import com.slusarz.pokerafterdark.spring.token.TokenTimeoutProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class AuthenticationService {

    private PasswordEncoder passwordEncoder;

    private PasswordProvider passwordProvider;

    private TokenGenerator tokenGenerator;

    private TokenTimeoutProvider tokenTimeoutProvider;

    private Set<Token> tokens;

    public AuthenticationService(PasswordEncoder passwordEncoder,
                                 PasswordProvider passwordProvider,
                                 TokenGenerator tokenGenerator,
                                 TokenTimeoutProvider tokenTimeoutProvider) {
        this.passwordEncoder = passwordEncoder;
        this.passwordProvider = passwordProvider;
        this.tokenGenerator = tokenGenerator;
        this.tokenTimeoutProvider = tokenTimeoutProvider;
        this.tokens = new HashSet<>();
    }

    public Optional<Token> authenticate(String password) {
        Token token = null;
        if (passwordEncoder.matches(password, passwordProvider.getPassword())) {
            token = tokenGenerator.generate();
            tokens.add(token);
        }
        return Optional.ofNullable(token);
    }

    public boolean isAuthenticated(String enteredToken) {
        Optional<Token> foundToken
                = tokens.stream().filter(storedToken -> storedToken.getValue().equals(enteredToken)).findFirst();
        if (!foundToken.isPresent()) {
            return false;
        }
        Token token = foundToken.get();
        if (token.isExpired()) {
            log.info("Remove token [" + enteredToken + "] due to expiration.");
            tokens.removeIf(storedToken -> storedToken.getValue().equals(enteredToken));
            return false;
        }
        log.info("Prolongate token [" + token + "].");
        token.prolongate(tokenTimeoutProvider.getTimeout());
        return true;
    }

    public void removeExpiredTokens() {
        tokens.removeIf(Token::isExpired);
    }

}
