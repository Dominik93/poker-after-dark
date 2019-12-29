package com.slusarz.pokerafterdark.spring.delivery.mapper.command;

import com.slusarz.pokerafterdark.aplication.cqrs.command.Command;
import com.slusarz.pokerafterdark.aplication.cqrs.command.CommandResult;

public interface CommandMapper<REQUEST, COMMAND extends Command, COMMAND_RESULT extends CommandResult, RESPONSE>
        extends RequestMapper<REQUEST, COMMAND>, ResultMapper<COMMAND_RESULT, RESPONSE> {

}
