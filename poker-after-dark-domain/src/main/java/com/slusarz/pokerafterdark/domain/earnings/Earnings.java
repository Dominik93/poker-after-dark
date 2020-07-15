package com.slusarz.pokerafterdark.domain.earnings;

import lombok.Value;

@Value(staticConstructor = "of")
public class Earnings {

    private double value;

    public static Earnings zero() {
        return new Earnings(0);
    }

}
