package com.slusarz.pokerafterdark.domain.cashgame;

import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.pot.Pot;
import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import com.slusarz.pokerafterdark.domain.validation.ValidationExecutor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CashGame {

    @Valid
    @NotNull(message = ValidationError.MANDATORY_GAME_ID)
    private CashGameId cashGameId;

    @Valid
    @NotNull(message = ValidationError.MANDATORY_HOST_ID)
    private PlayerId host;

    @NotNull(message = ValidationError.MANDATORY_GAME_DATE)
    private LocalDate occurrenceDate;

    @Valid
    @NotNull(message = ValidationError.MANDATORY_POT)
    private Pot pot;

    @Valid
    @NotEmpty(message = ValidationError.EMPTY_CASH_GAME_PARTICIPANTS)
    private List<CashGameParticipant> cashGameParticipants;

    public static CashGame of(CashGameId cashGameId,
                              PlayerId host,
                              LocalDate occurrenceDate,
                              Pot pot,
                              List<CashGameParticipant> cashGameParticipants) {
        return ValidationExecutor.validateAndReturn(new CashGame(cashGameId, host, occurrenceDate, pot, cashGameParticipants));
    }
}
