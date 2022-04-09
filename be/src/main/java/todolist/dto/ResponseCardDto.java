package todolist.dto;

public class ResponseCardDto {

    private Long id;
    private String section;
    private String title;
    private String content;

    public ResponseCardDto(Long id, String section, String title, String content) {
        this.id = id;
        this.section = section;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}

//이름
//id
