package com.codesquad.todolist.util.page;

public class Criteria {

    private final int length;
    private final int size;

    public Criteria(int length, int size) {
        this.length = Math.max(length, 0);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public int getLimit() {
        return size + 1;
    }

    public int getOffset() {
        return length;
    }

    @Override
    public String toString() {
        return "Criteria{" +
            "length=" + length +
            ", size=" + size +
            '}';
    }
}
