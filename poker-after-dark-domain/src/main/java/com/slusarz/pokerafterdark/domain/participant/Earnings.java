package com.slusarz.pokerafterdark.domain.participant;

import lombok.Value;

@Value(staticConstructor = "of")
public class Earnings {

    private double value;

    public static Earnings zero() {
        return new Earnings(0);
    }

}
