export default class TodoEdit {
  constructor(id, title, content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }
  render = () => {
    return /*html*/ `
        <article class="edit-input-wrapper">
            <input class="edit-input-header" value =${this.title} placeholder="제목을 입력하세요" />
            <textarea class="edit-input-content" placeholder="내용을 입력하세요" maxlength ='500' >${this.content}</textarea>
            <div class="input-button-wrapper">
                <button class="input__button input--cancel">취소</button>
                <button class="input__button input--update bg-sky-blue">수정</button>
            </div>
        </article>
    `;
  };
}
