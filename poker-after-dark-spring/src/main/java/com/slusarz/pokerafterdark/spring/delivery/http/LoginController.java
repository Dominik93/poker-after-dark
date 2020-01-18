package com.slusarz.pokerafterdark.spring.delivery.http;

import com.slusarz.pokerafterdark.specification.model.login.LoginRequest;
import com.slusarz.pokerafterdark.specification.model.login.LoginResponse;
import com.slusarz.pokerafterdark.spring.authentication.AuthenticationService;
import com.slusarz.pokerafterdark.spring.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(LoginController.LOGIN)
public class LoginController {

    public static final String LOGIN = "login";

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        Optional<Token> token = authenticationService.authenticate(loginRequest.getPassword());
        return LoginResponse.builder()
                .success(token.isPresent())
                .token(token.map(Token::getValue).orElse(null))
                .build();
    }
}
