package com.slusarz.pokerafterdark.spring.cqrs;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HandlerNameUtil {

    public static String getHandlerClassName(Class handler) {
        String genericClass = handler.toGenericString();
        return reduceHandler(reduceEnhancer(reduceClass(genericClass)));
    }

    private static String reduceEnhancer(String genericClass) {
        return genericClass.split("\\$\\$")[0];
    }

    private static String reduceClass(String genericClass) {
        return genericClass.split(" ")[2];
    }

    private static String reduceHandler(String genericClass) {
        return genericClass.replace("Handler", "");
    }

}
