package com.slusarz.pokerafterdark.application.common.cqrs;

import com.slusarz.pokerafterdark.application.common.cqrs.command.Command;
import com.slusarz.pokerafterdark.application.common.cqrs.command.CommandResult;
import com.slusarz.pokerafterdark.application.common.cqrs.query.Query;
import com.slusarz.pokerafterdark.application.common.cqrs.query.QueryResult;

public interface ServiceExecutor {

    QueryResult executeQuery(Query query);

    CommandResult executeCommand(Command command);

}
