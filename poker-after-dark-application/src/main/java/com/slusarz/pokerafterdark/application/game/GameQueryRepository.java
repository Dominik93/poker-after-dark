package com.slusarz.pokerafterdark.application.game;

import com.slusarz.pokerafterdark.domain.player.PlayerId;

import java.time.LocalDate;
import java.util.List;

public interface GameQueryRepository {

    List<GameProjection> read(LocalDate from, LocalDate to, List<PlayerId> playerIds);
}
