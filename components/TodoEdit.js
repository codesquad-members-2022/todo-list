export default class TodoEdit {
  constructor(id, content) {
    this.id = id;
    this.content = content;
  }
  render = () => {
    return /*html*/ `
        <article class="edit-input-wrapper">
            <input class="input-header" value =${this.id} placeholder="제목을 입력하세요" />
            <input class="input-content" value=${this.content} placeholder="내용을 입력하세요" maxlength ='500' />
            <div class="input-button-wrapper">
                <button class="input__button edit-input--cancel">취소</button>
                <button class="input__button edit-input--register bg-sky-blue">수정</button>
            </div>
        </article>
    `;
  };
}
