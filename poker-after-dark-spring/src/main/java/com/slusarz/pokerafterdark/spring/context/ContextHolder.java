package com.slusarz.pokerafterdark.spring.context;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextHolder {

    private static InheritableThreadLocal<Context> currentContext = new InheritableThreadLocal<>();

    public static Context getContext() {
        return currentContext.get();
    }

    public static void setContext(Context context) {
        log.info("Set context [" + context + "]");
        currentContext.set(context);
    }

    public static void clear() {
        currentContext.set(null);
    }

}
