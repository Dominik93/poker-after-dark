package com.slusarz.pokerafterdark.application.usecase.addgame;

import com.slusarz.pokerafterdark.application.cqrs.command.Command;
import com.slusarz.pokerafterdark.domain.game.Pot;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value(staticConstructor = "of")
public class AddGameCommand implements Command {

    private PlayerId host;

    private LocalDate date;

    private Pot pot;

    private List<Participant> participants;

    private boolean skipPotValidation;

}
