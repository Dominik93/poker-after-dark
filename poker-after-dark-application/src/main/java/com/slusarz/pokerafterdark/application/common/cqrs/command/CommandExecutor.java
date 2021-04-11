package com.slusarz.pokerafterdark.application.common.cqrs.command;

public interface CommandExecutor {

    CommandResult execute(Command command);

}
