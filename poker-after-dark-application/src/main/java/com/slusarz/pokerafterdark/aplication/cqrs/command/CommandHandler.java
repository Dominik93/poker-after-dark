package com.slusarz.pokerafterdark.aplication.cqrs.command;

import com.slusarz.pokerafterdark.aplication.cqrs.handler.Handler;

public interface CommandHandler<RESULT extends CommandResult, COMMAND_TYPE extends Command> extends Handler<RESULT, COMMAND_TYPE> {

    RESULT handle(COMMAND_TYPE command);

}
