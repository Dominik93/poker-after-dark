package com.slusarz.pokerafterdark.spring.common.delivery.command;

import com.slusarz.pokerafterdark.application.common.cqrs.command.CommandResult;

public interface ResultMapper<COMMAND_RESULT extends CommandResult, RESPONSE> {

    RESPONSE toResponse(COMMAND_RESULT commandResult);

}
