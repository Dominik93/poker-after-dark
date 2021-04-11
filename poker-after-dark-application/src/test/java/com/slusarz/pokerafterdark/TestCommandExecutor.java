package com.slusarz.pokerafterdark;

import com.slusarz.pokerafterdark.application.common.cqrs.command.Command;
import com.slusarz.pokerafterdark.application.common.cqrs.command.CommandHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TestCommandExecutor {

    private ExceptionsHandler exceptionsHandler;

    public <T> T execute(CommandHandler commandHandler, Command command) {
        try {
            return (T) commandHandler.handle(command);
        } catch (Exception e) {
            exceptionsHandler.put(e);
        }
        return null;
    }

}
