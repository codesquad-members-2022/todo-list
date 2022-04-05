package team07.todolist.dto;

public class ResponseCard {

  private String userId;
  private String title;
  private String content;
  private Integer row;
  private Integer status;

  public ResponseCard() {
  }

  public ResponseCard(String userId, String title, String content, Integer row, Integer status) {
    this.userId = userId;
    this.title = title;
    this.content = content;
    this.row = row;
    this.status = status;
  }

  public String getUserId() {
    return userId;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public Integer getRow() {
    return row;
  }

  public Integer getStatus() {
    return status;
  }

  @Override
  public String toString() {
    return "ResponseCard{" +
        "userId='" + userId + '\'' +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", row=" + row +
        ", status=" + status +
        '}';
  }
}
