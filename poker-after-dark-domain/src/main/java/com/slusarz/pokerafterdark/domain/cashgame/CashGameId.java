package com.slusarz.pokerafterdark.domain.cashgame;

import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value(staticConstructor = "of")
public class CashGameId {

    @NotBlank(message = ValidationError.MANDATORY_GAME_ID)
    private String id;

}
