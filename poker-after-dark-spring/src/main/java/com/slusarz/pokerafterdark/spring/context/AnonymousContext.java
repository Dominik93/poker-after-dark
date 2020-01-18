package com.slusarz.pokerafterdark.spring.context;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class AnonymousContext implements Context {

    @Override
    public boolean isAdministrationMode() {
        return false;
    }
}
