package com.slusarz.pokerafterdark.spring.delivery.mapper.command;

import com.slusarz.pokerafterdark.aplication.cqrs.command.CommandResult;

public interface ResultMapper<COMMAND_RESULT extends CommandResult, RESPONSE> {

    RESPONSE toResponse(COMMAND_RESULT commandResult);

}
