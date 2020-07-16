package com.slusarz.pokerafterdark.infrastructure.persistence.repository.game.result;

import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import com.slusarz.pokerafterdark.domain.tournament.Place;
import lombok.Value;

@Value(staticConstructor = "of")
public class ParticipationResult {

    private GameId gameId;

    private PlayerId playerId;

    private PlayerName playerName;

    private Earnings earnings;

    private Place place;
}
