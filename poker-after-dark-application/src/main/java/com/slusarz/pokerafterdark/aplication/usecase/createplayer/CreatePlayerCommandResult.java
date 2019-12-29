package com.slusarz.pokerafterdark.aplication.usecase.createplayer;

import com.slusarz.pokerafterdark.aplication.cqrs.command.CommandResult;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import lombok.Value;

@Value(staticConstructor = "of")
public class CreatePlayerCommandResult implements CommandResult {

    private PlayerId playerId;

}
