package com.slusarz.pokerafterdark.spring.cqrs;

import com.slusarz.pokerafterdark.application.cqrs.command.CommandHandler;
import com.slusarz.pokerafterdark.application.cqrs.handler.HandlerProvider;
import com.slusarz.pokerafterdark.spring.cqrs.exceptions.ExecutionException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class CommandHandlerProvider implements HandlerProvider {

    private Map<Class, CommandHandler> commandHandlers = new HashMap<>();

    public CommandHandlerProvider(List<CommandHandler> commandHandlers) {
        for (CommandHandler commandHandler : commandHandlers) {
            try {
                String handler = HandlerNameUtil.getHandlerClassName(commandHandler.getClass());
                this.commandHandlers.put(Class.forName(handler), commandHandler);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public CommandHandler getHandler(Object command) {
        CommandHandler commandHandler = commandHandlers.get(command.getClass());
        if (Objects.isNull(commandHandler) ){
            throw new ExecutionException(command);
        }
        return commandHandler;
    }
}
