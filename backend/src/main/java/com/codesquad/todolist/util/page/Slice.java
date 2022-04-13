package com.codesquad.todolist.util.page;

import java.util.List;

public class Slice<T> {

    private final boolean hasNext;
    private final List<T> data;

    public Slice(List<T> data, Criteria criteria) {
        int index = Math.min(data.size(), criteria.getSize());

        this.hasNext = data.size() > criteria.getSize();
        this.data = data.subList(0, index);
    }

    public boolean getHasNext() {
        return hasNext;
    }

    public List<T> getData() {
        return data;
    }

}
