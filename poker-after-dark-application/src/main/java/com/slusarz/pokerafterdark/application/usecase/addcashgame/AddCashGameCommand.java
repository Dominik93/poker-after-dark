package com.slusarz.pokerafterdark.application.usecase.addcashgame;

import com.slusarz.pokerafterdark.application.cqrs.command.Command;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameParticipant;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.pot.Pot;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value(staticConstructor = "of")
public class AddCashGameCommand implements Command {

    private PlayerId host;

    private LocalDate occurrenceDate;

    private Pot pot;

    private List<CashGameParticipant> cashGameParticipants;

    private boolean skipPotValidation;

}
