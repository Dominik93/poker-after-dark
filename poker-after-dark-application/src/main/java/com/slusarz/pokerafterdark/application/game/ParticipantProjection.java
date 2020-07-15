package com.slusarz.pokerafterdark.application.game;

import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import com.slusarz.pokerafterdark.domain.tournament.Place;
import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Value(staticConstructor = "of")
public class ParticipantProjection {

    @Valid
    @NotNull(message = ValidationError.MANDATORY_PLAYER_ID)
    private PlayerId playerId;

    @Valid
    @NotNull(message = ValidationError.MANDATORY_PLAYER_NAME)
    private PlayerName playerName;

    @Valid
    @NotNull(message = ValidationError.MANDATORY_EARNINGS)
    private Earnings earnings;

    @Valid
    private Place place;

    public Optional<Place> getPlace() {
        return Optional.ofNullable(place);
    }
}
