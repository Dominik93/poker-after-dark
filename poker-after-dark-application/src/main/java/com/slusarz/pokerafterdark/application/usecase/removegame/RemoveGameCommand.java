package com.slusarz.pokerafterdark.application.usecase.removegame;

import com.slusarz.pokerafterdark.application.common.cqrs.command.Command;
import com.slusarz.pokerafterdark.domain.game.GameId;
import lombok.Value;

@Value(staticConstructor = "of")
public class RemoveGameCommand implements Command {

    private GameId gameId;

}
