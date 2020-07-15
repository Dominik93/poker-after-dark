package com.slusarz.pokerafterdark.domain.tournament;

import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value(staticConstructor = "of")
public class TournamentId {

    @NotBlank(message = ValidationError.MANDATORY_TOURNAMENT_ID)
    private String id;

}
