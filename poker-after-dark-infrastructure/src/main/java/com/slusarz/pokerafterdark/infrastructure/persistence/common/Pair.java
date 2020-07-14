package com.slusarz.pokerafterdark.infrastructure.persistence.common;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Pair<K, V> { // todo use apache common? or other lib?

    private final K key;

    private final V value;

}
