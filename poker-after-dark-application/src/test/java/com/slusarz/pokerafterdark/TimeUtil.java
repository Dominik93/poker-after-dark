package com.slusarz.pokerafterdark;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUtil {

    public static boolean isAfter(final String text){
        return Arrays.asList("after").contains(text);
    }

}
