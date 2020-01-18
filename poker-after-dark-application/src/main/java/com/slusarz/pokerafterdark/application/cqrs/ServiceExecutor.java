package com.slusarz.pokerafterdark.application.cqrs;

import com.slusarz.pokerafterdark.application.cqrs.command.Command;
import com.slusarz.pokerafterdark.application.cqrs.command.CommandResult;
import com.slusarz.pokerafterdark.application.cqrs.query.Query;
import com.slusarz.pokerafterdark.application.cqrs.query.QueryResult;

public interface ServiceExecutor {

    QueryResult executeQuery(Query query);

    CommandResult executeCommand(Command command);

}
