package com.slusarz.pokerafterdark.spring.authentication;

public interface PasswordMatcher {

    boolean matches(String password);

}
