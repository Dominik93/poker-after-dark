package com.slusarz.pokerafterdark.spring.context;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class AdministrationContext implements Context {

    private String token;

    @Override
    public boolean isAdministrationMode() {
        return true;
    }
}
