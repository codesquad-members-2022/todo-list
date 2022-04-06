export default class TodoInput {
  constructor(title) {
    this.title = title;
    // this.render();
  }

  template = () => {
    return /*html*/ `
        <article class="input-wrapper input-${this.title}">
            <input class="input-header"placeholder="제목을 입력하세요" />
            <input class="input-content" placeholder="내용을 입력하세요" maxlength ='500' />
            <div class="input-button-wrapper">
                <button class="input__button input--cancel">취소</button>
                <button class="input__button input--register">등록</button>
            </div>
        </article>
    `;
  };
}
