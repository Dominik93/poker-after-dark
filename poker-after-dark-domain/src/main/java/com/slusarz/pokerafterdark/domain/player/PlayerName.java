package com.slusarz.pokerafterdark.domain.player;

import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value(staticConstructor = "of")
public class PlayerName {

    @NotBlank(message = ValidationError.MANDATORY_PLAYER_NAME)
    private String name;

    public static PlayerName unknown() {
        return new PlayerName("unknown");
    }
}
