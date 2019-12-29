package com.slusarz.pokerafterdark.domain.player;

import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.validation.ValidationExecutor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Player {

    @Valid
    @NotNull
    private PlayerId playerId;

    @Valid
    @NotNull
    private PlayerName playerName;

    @Valid
    @NotNull
    private Earnings liveEarnings;

    @Valid
    @NotNull
    private NumberOfPlays numberOfPlays;

    public static Player of(PlayerId playerId, PlayerName playerName, Earnings liveEarnings, NumberOfPlays numberOfPlays) {
        return ValidationExecutor.validateAndReturn(new Player(playerId, playerName, liveEarnings, numberOfPlays));
    }

    public static Player newPlayer(PlayerId playerId, PlayerName playerName) {
        return ValidationExecutor.validateAndReturn(new Player(playerId, playerName, Earnings.of(0), NumberOfPlays.of(0)));
    }

    public void addWin(Double value) {
        this.numberOfPlays = NumberOfPlays.of(numberOfPlays.getValue() + 1);
        this.liveEarnings = Earnings.of(liveEarnings.getValue() + value);
    }

}
