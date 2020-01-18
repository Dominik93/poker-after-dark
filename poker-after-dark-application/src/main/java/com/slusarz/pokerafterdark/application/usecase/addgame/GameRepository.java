package com.slusarz.pokerafterdark.application.usecase.addgame;

import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.GameId;

public interface GameRepository {

    GameId generateId();

    Game save(Game game);

    void remove(GameId gameId);
}
