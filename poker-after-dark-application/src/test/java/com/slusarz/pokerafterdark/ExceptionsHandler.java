package com.slusarz.pokerafterdark;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ExceptionsHandler {

    private Map<Class, List<Exception>> exceptions = new HashMap<>();

    public void put(Exception e) {
        log.info("Exception [" + e + "] was added.");
        if (!exceptions.containsKey(e.getClass())) {
            exceptions.put(e.getClass(), new ArrayList<>());
        }
        exceptions.get(e.getClass()).add(e);
    }

    public List<Exception> get(Class exceptionClass) {
        return exceptions.getOrDefault(exceptionClass, Collections.emptyList());
    }

    public int size(Class e) {
        return exceptions.getOrDefault(e, Collections.emptyList()).size();
    }

    public boolean isEmpty() {
        return exceptions.isEmpty();
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

}
