package com.slusarz.pokerafterdark.application.cqrs.command;

public interface CommandExecutor {

    CommandResult execute(Command command);

}
