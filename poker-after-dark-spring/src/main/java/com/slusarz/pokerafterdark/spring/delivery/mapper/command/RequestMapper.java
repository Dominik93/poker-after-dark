package com.slusarz.pokerafterdark.spring.delivery.mapper.command;

import com.slusarz.pokerafterdark.aplication.cqrs.command.Command;

public interface RequestMapper<REQUEST, COMMAND extends Command> {

    COMMAND toCommand(REQUEST request);

}
