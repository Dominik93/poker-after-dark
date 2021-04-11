package com.slusarz.pokerafterdark.spring.delivery.mapper;

import com.slusarz.pokerafterdark.application.usecase.addcashgame.AddCashGameCommand;
import com.slusarz.pokerafterdark.application.usecase.addcashgame.AddCashGameCommandResult;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameParticipant;
import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.pot.Pot;
import com.slusarz.pokerafterdark.specification.api.AddCachGameRequest;
import com.slusarz.pokerafterdark.specification.api.AddGameResponse;
import com.slusarz.pokerafterdark.specification.api.CashGame;
import com.slusarz.pokerafterdark.spring.common.delivery.command.CommandMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AddCashGameMapper implements CommandMapper<AddCachGameRequest, AddCashGameCommand, AddCashGameCommandResult, AddGameResponse> {

    @Override
    public AddGameResponse toResponse(AddCashGameCommandResult result) {
        return AddGameResponse.builder().success(true).build();
    }

    @Override
    public AddCashGameCommand toCommand(AddCachGameRequest addCachGameRequest) {
        return toAddGameCommand(addCachGameRequest.getGame(), addCachGameRequest.getSkipValidation());
    }

    private AddCashGameCommand toAddGameCommand(CashGame game, boolean skipValidation) {
        PlayerId playerId = PlayerId.of(game.getHost().getId());
        Pot pot = Pot.of(game.getPot());
        LocalDate date = Optional.ofNullable(game.getDate()).orElse(LocalDate.now());
        List<CashGameParticipant> cashGameParticipants = toParticipants(game.getParticipants());
        return AddCashGameCommand.of(playerId, date, pot, cashGameParticipants, skipValidation);
    }

    private List<CashGameParticipant> toParticipants(List<com.slusarz.pokerafterdark.specification.api.CashGameParticipant> participants) {
        return participants.stream().map(this::toParticipant).collect(Collectors.toList());
    }

    private CashGameParticipant toParticipant(com.slusarz.pokerafterdark.specification.api.CashGameParticipant participant) {
        return CashGameParticipant.of(PlayerId.of(participant.getPlayerId()), Earnings.of(participant.getEarnings().doubleValue()));
    }
}
