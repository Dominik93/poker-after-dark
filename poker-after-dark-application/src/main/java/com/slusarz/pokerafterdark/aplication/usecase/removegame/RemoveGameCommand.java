package com.slusarz.pokerafterdark.aplication.usecase.removegame;

import com.slusarz.pokerafterdark.aplication.cqrs.command.Command;
import com.slusarz.pokerafterdark.domain.game.GameId;
import lombok.Value;

@Value(staticConstructor = "of")
public class RemoveGameCommand implements Command {

    private GameId gameId;

}
