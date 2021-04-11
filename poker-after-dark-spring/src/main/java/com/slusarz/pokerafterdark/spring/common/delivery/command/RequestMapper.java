package com.slusarz.pokerafterdark.spring.common.delivery.command;

import com.slusarz.pokerafterdark.application.common.cqrs.command.Command;

public interface RequestMapper<REQUEST, COMMAND extends Command> {

    COMMAND toCommand(REQUEST request);

}
