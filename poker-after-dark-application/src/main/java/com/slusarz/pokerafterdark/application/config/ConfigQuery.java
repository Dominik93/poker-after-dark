package com.slusarz.pokerafterdark.application.config;

import com.slusarz.pokerafterdark.application.cqrs.query.Query;
import lombok.Value;

@Value(staticConstructor = "of")
public class ConfigQuery implements Query {
}
