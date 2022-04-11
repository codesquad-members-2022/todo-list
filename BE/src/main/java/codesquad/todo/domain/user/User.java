package codesquad.todo.domain.user;

public class User {

    private Long id;
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void initId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
