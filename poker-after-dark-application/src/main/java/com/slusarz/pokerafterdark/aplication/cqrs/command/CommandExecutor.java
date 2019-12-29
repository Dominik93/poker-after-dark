package com.slusarz.pokerafterdark.aplication.cqrs.command;

public interface CommandExecutor {

    CommandResult execute(Command command);

}
