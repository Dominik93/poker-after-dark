package com.slusarz.pokerafterdark.application.usecase.addcashgame;

import com.slusarz.pokerafterdark.application.common.cqrs.command.CommandHandler;
import com.slusarz.pokerafterdark.application.common.events.EventBus;
import com.slusarz.pokerafterdark.application.common.permission.RequiredAdministrationPermission;
import com.slusarz.pokerafterdark.application.usecase.addcashgame.event.AddCashGameEvent;
import com.slusarz.pokerafterdark.application.usecase.addgame.validator.AddGameValidator;
import com.slusarz.pokerafterdark.domain.cashgame.CashGame;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameId;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameParticipant;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameRepository;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.pot.Pot;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AddCashGameCommandHandler implements CommandHandler<AddCashGameCommandResult, AddCashGameCommand> {

    private EventBus eventsBus;

    private CashGameRepository cashGameRepository;

    private AddGameValidator addGameValidator;

    @Override
    @RequiredAdministrationPermission
    public AddCashGameCommandResult handle(AddCashGameCommand addCashGameCommand) {
        validate(addCashGameCommand);
        CashGame cashGame = createCashGame(addCashGameCommand);
        cashGameRepository.save(cashGame);
        eventsBus.fireEvent(AddCashGameEvent.of(cashGame));
        return AddCashGameCommandResult.of();
    }

    private CashGame createCashGame(AddCashGameCommand addCashGameCommand) {
        CashGameId cashGameId = cashGameRepository.generateId();
        PlayerId host = addCashGameCommand.getHost();
        LocalDate occurrenceDate = addCashGameCommand.getOccurrenceDate();
        Pot pot = addCashGameCommand.getPot();
        List<CashGameParticipant> cashGameParticipants = addCashGameCommand.getCashGameParticipants();
        return CashGame.of(cashGameId, host, occurrenceDate, pot, cashGameParticipants);
    }

    private void validate(AddCashGameCommand command) {
        addGameValidator.validateOccurrenceDate(command.getOccurrenceDate());
        validatePot(command);
    }

    private void validatePot(AddCashGameCommand command) {
        if (!command.isSkipPotValidation()) {
            addGameValidator.validatePot(command.getCashGameParticipants().stream()
                    .map(CashGameParticipant::getEarnings).collect(Collectors.toList()));
        }
    }

}
