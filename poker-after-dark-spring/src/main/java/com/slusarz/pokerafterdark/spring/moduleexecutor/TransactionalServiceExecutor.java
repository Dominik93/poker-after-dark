package com.slusarz.pokerafterdark.spring.moduleexecutor;

import com.slusarz.pokerafterdark.application.cqrs.ServiceExecutor;
import com.slusarz.pokerafterdark.application.cqrs.command.Command;
import com.slusarz.pokerafterdark.application.cqrs.command.CommandExecutor;
import com.slusarz.pokerafterdark.application.cqrs.command.CommandResult;
import com.slusarz.pokerafterdark.application.cqrs.query.Query;
import com.slusarz.pokerafterdark.application.cqrs.query.QueryExecutor;
import com.slusarz.pokerafterdark.application.cqrs.query.QueryResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
public class TransactionalServiceExecutor implements ServiceExecutor {

    private QueryExecutor queryExecutor;

    private CommandExecutor commandExecutor;

    @Transactional
    public QueryResult executeQuery(Query query) {
        return queryExecutor.execute(query);
    }

    @Transactional
    public CommandResult executeCommand(Command command) {
        return commandExecutor.execute(command);
    }

}
