package com.codesquad.todolist.util.page;

public class Criteria {

    private final int page;
    private final int size;

    public Criteria(int page, int size) {
        this.page = Math.max(page, 1);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public int getLimit() {
        return size + 1;
    }

    public int getOffset() {
        return (page - 1) * size;
    }

    @Override
    public String toString() {
        return "Criteria{" +
            "page=" + page +
            ", size=" + size +
            '}';
    }
}
