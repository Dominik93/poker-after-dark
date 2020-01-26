package com.slusarz.pokerafterdark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExceptionsHandler {

    private Map<Class, List<Exception>> exceptions = new HashMap<>();

    public void put(Exception e) {
        if (!exceptions.containsKey(e.getClass())) {
            exceptions.put(e.getClass(), new ArrayList<>());
        }
        exceptions.get(e.getClass()).add(e);
    }

    public List<Exception> get(Class exceptionClass) {
        return exceptions.get(exceptionClass);
    }


    public int size(Class e) {
        return exceptions.getOrDefault(e, Collections.emptyList()).size();
    }

    public int size() {
        return (int) exceptions.values().stream().mapToLong(List::size).sum();
    }
}
