package com.slusarz.pokerafterdark.domain.profit;

import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import com.slusarz.pokerafterdark.domain.validation.ValidationError;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Value(staticConstructor = "of")
public class Profit {

    @Valid
    @NotNull(message = ValidationError.MANDATORY_PLAYER_ID)
    private PlayerId playerId;

    @Valid
    @NotNull(message = ValidationError.MANDATORY_PLAYER_NAME)
    private PlayerName playerName;

    @Valid
    @NotNull(message = ValidationError.MANDATORY_WINNINGS)
    private List<Earnings> winnings;
}
