package com.slusarz.pokerafterdark.spring.cqrs;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HandlerNameUtil {

    public static String getHandlerClassName(Class handler) {
        String genericString = handler.toGenericString();
        return genericString.split(" ")[2].replace("Handler", "");
    }

}
