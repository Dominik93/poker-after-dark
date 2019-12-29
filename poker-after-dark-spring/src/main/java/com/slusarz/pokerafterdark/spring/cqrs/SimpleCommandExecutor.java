package com.slusarz.pokerafterdark.spring.cqrs;

import com.slusarz.pokerafterdark.aplication.cqrs.command.Command;
import com.slusarz.pokerafterdark.aplication.cqrs.command.CommandExecutor;
import com.slusarz.pokerafterdark.aplication.cqrs.command.CommandResult;
import com.slusarz.pokerafterdark.aplication.cqrs.handler.HandlerProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class SimpleCommandExecutor implements CommandExecutor {

    private HandlerProvider<CommandResult, Command> handlerProvider;

    public CommandResult execute(Command command) {
        log.info("Execute command [" + command + "]");
        long ttime = System.currentTimeMillis();
        CommandResult queryResult = handlerProvider.getHandler(command).handle(command);
        log.info("Query executed in " + (System.currentTimeMillis() - ttime) + "ms, result = [" + queryResult + "]");
        return queryResult;
    }

}
