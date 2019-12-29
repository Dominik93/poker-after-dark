package com.slusarz.pokerafterdark.aplication.player;

import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.player.NumberOfPlays;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import lombok.Value;

@Value(staticConstructor = "of")
public class PlayerProjection {

    private PlayerId playerId;

    private PlayerName playerName;

    private Earnings liveEarnings;

    private Earnings biggestWin;

    private Earnings biggestLose;

    private NumberOfPlays numberOfPlays;
}
