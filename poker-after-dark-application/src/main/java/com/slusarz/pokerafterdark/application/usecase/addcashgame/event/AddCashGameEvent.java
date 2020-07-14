package com.slusarz.pokerafterdark.application.usecase.addcashgame.event;

import com.slusarz.pokerafterdark.application.events.Event;
import com.slusarz.pokerafterdark.domain.cashgame.CashGame;
import lombok.Value;

@Value(staticConstructor = "of")
public class AddCashGameEvent implements Event {

    private CashGame cashGame;

}
