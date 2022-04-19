package codesquad.todo.domain.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
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

    public boolean isSameId(Long userId) {
        return this.id.equals(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
