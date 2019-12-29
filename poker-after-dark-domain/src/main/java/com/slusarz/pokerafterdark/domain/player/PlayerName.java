package com.slusarz.pokerafterdark.domain.player;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value(staticConstructor = "of")
public class PlayerName {

    @NotBlank
    private String name;

    public static PlayerName unknown() {
        return new PlayerName("unknown");
    }
}
