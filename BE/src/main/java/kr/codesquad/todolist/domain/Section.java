package kr.codesquad.todolist.domain;

public class Section {

    private final Integer id;
    private final String name;

    public Section(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
