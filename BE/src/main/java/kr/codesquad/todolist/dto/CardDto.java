package kr.codesquad.todolist.dto;

public class CardDto {

    private final String author;
    private final String subject;
    private final String contents;

    public CardDto(String author, String subject, String contents) {
        this.author = author;
        this.subject = subject;
        this.contents = contents;
    }

    public String getAuthor() {
        return author;
    }

    public String getSubject() {
        return subject;
    }

    public String getContents() {
        return contents;
    }



}
