package com.slusarz.pokerafterdark.aplication.cqrs;

import com.slusarz.pokerafterdark.aplication.cqrs.command.Command;
import com.slusarz.pokerafterdark.aplication.cqrs.command.CommandResult;
import com.slusarz.pokerafterdark.aplication.cqrs.query.Query;
import com.slusarz.pokerafterdark.aplication.cqrs.query.QueryResult;

public interface ServiceExecutor {

    QueryResult executeQuery(Query query);

    CommandResult executeCommand(Command command);

}
