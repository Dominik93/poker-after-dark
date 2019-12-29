package com.slusarz.pokerafterdark.infrastructure.persistence.common;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Pair<K, V> {

    private final K key;

    private final V value;

}
