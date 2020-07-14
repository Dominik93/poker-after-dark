package com.slusarz.pokerafterdark.domain.game;

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
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Game {

    @Valid
    @NotNull(message = ValidationError.MANDATORY_GAME_ID)
    private GameId gameId;

    @Valid
    @NotNull(message = ValidationError.MANDATORY_HOST_ID)
    private PlayerId host;

    @NotNull(message = ValidationError.MANDATORY_GAME_DATE)
    private LocalDate occurrenceDate;

    @Valid
    @NotNull(message = ValidationError.MANDATORY_POT)
    private Pot pot;

    public static Game of(GameId cashGameId, PlayerId host, LocalDate occurrenceDate, Pot pot) {
        return ValidationExecutor.validateAndReturn(new Game(cashGameId, host, occurrenceDate, pot));
    }
}
