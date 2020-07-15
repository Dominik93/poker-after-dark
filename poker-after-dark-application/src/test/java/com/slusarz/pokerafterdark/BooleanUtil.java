package com.slusarz.pokerafterdark;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BooleanUtil {

    public static boolean convertString(final String text){
        return Arrays.asList("have", "emitted", "added", "created", "was", "matches").contains(text);
    }

}
