package com.codesquad.todolist.util;

import org.springframework.jdbc.support.KeyHolder;

public interface KeyHolderFactory {

    KeyHolder newKeyHolder();

}
