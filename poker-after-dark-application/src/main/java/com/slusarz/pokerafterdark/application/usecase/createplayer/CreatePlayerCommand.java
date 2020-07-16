package com.slusarz.pokerafterdark.application.usecase.createplayer;

import com.slusarz.pokerafterdark.application.common.cqrs.command.Command;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import lombok.Value;

@Value(staticConstructor = "of")
public class CreatePlayerCommand implements Command {

    private PlayerName playerName;


}
