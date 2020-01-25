package com.slusarz.pokerafterdark.spring.configuration.application;

import com.slusarz.pokerafterdark.application.cqrs.ServiceExecutor;
import com.slusarz.pokerafterdark.application.cqrs.command.Command;
import com.slusarz.pokerafterdark.application.cqrs.command.CommandExecutor;
import com.slusarz.pokerafterdark.application.cqrs.command.CommandHandler;
import com.slusarz.pokerafterdark.application.cqrs.command.CommandResult;
import com.slusarz.pokerafterdark.application.cqrs.handler.HandlerProvider;
import com.slusarz.pokerafterdark.application.cqrs.query.Query;
import com.slusarz.pokerafterdark.application.cqrs.query.QueryExecutor;
import com.slusarz.pokerafterdark.application.cqrs.query.QueryHandler;
import com.slusarz.pokerafterdark.application.cqrs.query.QueryResult;
import com.slusarz.pokerafterdark.spring.cqrs.CommandHandlerProvider;
import com.slusarz.pokerafterdark.spring.cqrs.QueryHandlerProvider;
import com.slusarz.pokerafterdark.spring.cqrs.SimpleCommandExecutor;
import com.slusarz.pokerafterdark.spring.cqrs.SimpleQueryExecutor;
import com.slusarz.pokerafterdark.spring.moduleexecutor.TransactionalServiceExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CqrsConfiguration {

    @Bean
    public HandlerProvider commandHandlerProvider(List<CommandHandler> commandHandlers) {
        return new CommandHandlerProvider(commandHandlers);
    }

    @Bean
    public HandlerProvider queryHandlerProvider(List<QueryHandler> queryHandlers) {
        return new QueryHandlerProvider(queryHandlers);
    }

    @Bean
    public QueryExecutor queryExecutor(HandlerProvider<QueryResult, Query> queryHandlerProvider) {
        return new SimpleQueryExecutor(queryHandlerProvider);
    }

    @Bean
    public CommandExecutor commandExecutor(HandlerProvider<CommandResult, Command> commandHandlerProvider) {
        return new SimpleCommandExecutor(commandHandlerProvider);
    }

    @Bean
    public ServiceExecutor executor(QueryExecutor queryExecutor, CommandExecutor commandExecutor) {
        return new TransactionalServiceExecutor(queryExecutor, commandExecutor);
    }
}
