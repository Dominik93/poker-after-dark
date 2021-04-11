package com.slusarz.pokerafterdark;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BooleanUtil {

    public static boolean isFalse(String text) {
        return !isTrue(text);
    }

    public static boolean isTrue(String text) {
        return containsPositive(text) && !containsNot(text);
    }

    private static boolean containsPositive(String text) {
        return Stream.of("is", "have", "was", "that").anyMatch(text::startsWith);
    }

    private static boolean containsNot(String text) {
        return text.contains("not");
    }

}
