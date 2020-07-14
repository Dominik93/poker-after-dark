package com.slusarz.pokerafterdark.spring.authentication;

import com.slusarz.pokerafterdark.spring.token.Token;
import com.slusarz.pokerafterdark.spring.token.TokenGenerator;
import com.slusarz.pokerafterdark.spring.token.TokenTimeoutProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class AuthenticationService {

    private TokenGenerator tokenGenerator;

    private TokenTimeoutProvider tokenTimeoutProvider;

    private PasswordMatcher passwordMatcher;

    private Set<Token> tokens;

    public AuthenticationService(TokenGenerator tokenGenerator,
                                 TokenTimeoutProvider tokenTimeoutProvider,
                                 PasswordMatcher passwordMatcher) {
        this.tokenGenerator = tokenGenerator;
        this.passwordMatcher = passwordMatcher;
        this.tokenTimeoutProvider = tokenTimeoutProvider;
        this.tokens = new HashSet<>();
    }

    public Optional<Token> authenticate(String password) {
        Token token = null;
        if (passwordMatcher.matches(password)) {
            token = tokenGenerator.generate();
            tokens.add(token);
        }
        return Optional.ofNullable(token);
    }

    public void authenticateToken(String enteredToken) {
        Optional<Token> foundToken = searchForToken(enteredToken);
        if (foundToken.isPresent()) {
            Token token = foundToken.get();
            if (token.isExpired()) {
                removeExpiredToken(token);
            }
            prolongateToken(token);
        }
    }

    public boolean isAuthenticated(String enteredToken) {
        Optional<Token> foundToken = searchForToken(enteredToken);
        return foundToken.isPresent();
    }

    private Optional<Token> searchForToken(String enteredToken) {
        return tokens.stream()
                .filter(storedToken -> storedToken.getValue().equals(enteredToken))
                .findFirst();
    }

    public void removeExpiredTokens() {
        tokens.removeIf(Token::isExpired);
    }

    private void prolongateToken(Token token) {
        log.info("Prolongate token [" + token + "].");
        token.prolongate(tokenTimeoutProvider.getTimeout());
    }

    private void removeExpiredToken(Token enteredToken){
        log.info("Remove token [" + enteredToken + "] due to expiration.");
        tokens.removeIf(storedToken -> storedToken.getValue().equals(enteredToken.getValue()));
    }

}
