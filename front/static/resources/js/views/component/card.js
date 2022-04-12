class Card {
  constructor(props) {
    this.props = props;
  }

  static writableTemplate() {
    return `<li class="card card--write">
          <form action="">
            <input type="text" name="" id="" maxlength="50" placeholder="제목을 입력하세요" class="card__title" />
            <textarea
              name=""
              id=""
              maxlength="500"
              placeholder="내용을 입력하세요"
              class="card__content"
            ></textarea>
            <span class="card__writer">도니</span>
            <div class="button-wrap card__button-wrap">
              <button type="button" class="button--cancle card__button--cancle">취소</button>
              <button type="button" class="button--submit card__button--submit">등록</button>
            </div>
          </form>
        </li>`;
  }

  nomalTemplate() {
    return `<li class="card" data-id="${this.props.id}" data-status="${this.props.cardStatus}" data-index="${this.props.cardIndex}>
          <h3 class="card__title">${this.props.title}</h3>
          <div class="card__content">
            <p>${this.props.contents}</p>
          </div>
          <p class="card__writer">${this.props.writer}</p>
          <button type="button" class="button--delete card__button--delete">
            <span class="hidden-text">카드 삭제 버튼</span>
          </button>
        </li>`;
  }
}

export { Card };
