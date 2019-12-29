package com.slusarz.pokerafterdark.aplication.usecase.createplayer;

import com.slusarz.pokerafterdark.aplication.cqrs.command.Command;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import lombok.Value;

@Value(staticConstructor = "of")
public class CreatePlayerCommand implements Command {

    private PlayerName playerName;


}
