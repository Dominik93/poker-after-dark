package com.slusarz.pokerafterdark.infrastructure.persistence.result;

import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import lombok.Value;

@Value(staticConstructor = "of")
public class ParticipationResult {

    private GameId gameId;

    private PlayerId playerId;

    private Earnings earnings;
}
