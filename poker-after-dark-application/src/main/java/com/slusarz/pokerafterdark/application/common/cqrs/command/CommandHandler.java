package com.slusarz.pokerafterdark.application.common.cqrs.command;

import com.slusarz.pokerafterdark.application.common.cqrs.handler.Handler;

public interface CommandHandler<RESULT extends CommandResult, COMMAND_TYPE extends Command> extends Handler<RESULT, COMMAND_TYPE> {

    RESULT handle(COMMAND_TYPE command);

}
