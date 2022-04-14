package kr.codesquad.todolist.dto.card;

public class UpdateCardRequest {

    private String subject;
    private String contents;

    private UpdateCardRequest() {
    }

    public String getSubject() {
        return subject;
    }

    public String getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return "UpdateCardRequest{" +
                "subject='" + subject + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
