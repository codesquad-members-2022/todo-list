export default class TodoInput {
  constructor(title) {
    this.title = title;
    // this.render();
  }

  template = () => {
    return /*html*/ `
        <article class="input-wrapper input-${this.title}">
            <header class="input-header">제목을 입력하세요</header>
            <input class="input-todo" placeholder="내용을 입력하세요" />
            <div class="input-button-wrapper">
                <button class="input__button input--cancel">취소</button>
                <button class="input__button input--register">등록</button>
            </div>
        </article>
    `;
  };
}
