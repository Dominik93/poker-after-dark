package com.slusarz.pokerafterdark.domain.player;

import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import lombok.Value;

import javax.validation.constraints.Min;

@Value(staticConstructor = "of")
public class NumberOfPlays {

    @Min(value = 0, message = ValidationError.NUMBER_OF_PLAYS_BELOW_ZERO)
    private int value;

    public static NumberOfPlays zero(){
        return new NumberOfPlays(0);
    }

}
