package com.slusarz.pokerafterdark.aplication.config;

import com.slusarz.pokerafterdark.aplication.cqrs.query.Query;
import lombok.Value;

@Value(staticConstructor = "of")
public class ConfigQuery implements Query {
}
