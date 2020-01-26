package com.slusarz.pokerafterdark.domain.game;

import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
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
public class Game {

    @Valid
    @NotNull(message = ValidationError.MANDATORY_GAME_ID)
    private GameId gameId;

    @Valid
    @NotNull(message = ValidationError.MANDATORY_HOST_ID)
    private PlayerId host;

    @NotNull(message = ValidationError.MANDATORY_GAME_DATE)
    private LocalDate date;

    @Valid
    @NotNull(message = ValidationError.MANDATORY_POT)
    private Pot pot;

    @Valid
    @NotEmpty(message = ValidationError.EMPTY_PARTICIPANTS)
    private List<Participant> participants;

    public static Game of(GameId gameId, PlayerId host, LocalDate date, Pot pot, List<Participant> participants) {
        return ValidationExecutor.validateAndReturn(new Game(gameId, host, date, pot, participants));
    }
}
