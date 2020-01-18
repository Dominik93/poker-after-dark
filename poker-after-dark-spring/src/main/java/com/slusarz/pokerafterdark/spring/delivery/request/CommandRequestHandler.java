package com.slusarz.pokerafterdark.spring.delivery.request;

import com.slusarz.pokerafterdark.application.cqrs.ServiceExecutor;
import com.slusarz.pokerafterdark.application.cqrs.command.Command;
import com.slusarz.pokerafterdark.application.cqrs.command.CommandResult;
import com.slusarz.pokerafterdark.spring.delivery.mapper.command.CommandMapper;
import com.slusarz.pokerafterdark.spring.delivery.mapper.command.RequestMapper;
import com.slusarz.pokerafterdark.spring.delivery.mapper.command.ResultMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommandRequestHandler<RESPONSE, REQUEST, COMMAND_RESULT extends CommandResult> {

    private ServiceExecutor serviceExecutor;

    private ResultMapper responseMapper;

    private RequestMapper commandMapper;

    public CommandRequestHandler(ServiceExecutor serviceExecutor, CommandMapper commandMapper) {
        this.serviceExecutor = serviceExecutor;
        this.responseMapper = commandMapper;
        this.commandMapper = commandMapper;
    }

    public RESPONSE handle(REQUEST request) {
        Command command = commandMapper.toCommand(request);
        COMMAND_RESULT queryResult = (COMMAND_RESULT) serviceExecutor.executeCommand(command);
        return (RESPONSE) responseMapper.toResponse(queryResult);
    }

}
