package com.slusarz.pokerafterdark.domain.game;

import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value(staticConstructor = "of")
public class GameId {

    @NotBlank(message = ValidationError.MANDATORY_GAME_ID)
    private String id;

}
