package com.slusarz.pokerafterdark.domain.player;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value(staticConstructor = "of")
public class PlayerId {

    @NotNull
    private String id;

    public static PlayerId empty() {
        return new PlayerId("");
    }

}
