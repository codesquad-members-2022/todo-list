package com.codesquad.todolist.util;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class GeneratedKeyHolderFactory implements KeyHolderFactory {

    @Override
    public KeyHolder newKeyHolder() {
        return new GeneratedKeyHolder();
    }
}
