package com.slusarz.pokerafterdark.domain.player;

import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value(staticConstructor = "of")
public class PlayerId {

    @NotNull(message = ValidationError.MANDATORY_PLAYER_ID)
    private String id;

    public static PlayerId empty() {
        return new PlayerId("");
    }

}
