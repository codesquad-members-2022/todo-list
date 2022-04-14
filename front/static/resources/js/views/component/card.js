class Card {
  #CARD_TITLE_PLACEHLODER = '제목을 입력하세요';
  #CARD_CONTENT_PLACEHLODER = '내용을 입력하세요';

  constructor(props) {
    this.props = props;
    this.completion = true;
  }

  hasInputValue() {
    return this.props.title && this.props.contents ? true : false;
  }

  getTitle($card) {
    return $card.querySelector('.card__title').value;
  }

  getContents($card) {
    return $card.querySelector('.card__content').value;
  }

  writableTemplate() {
    return `<li class="card card--write" data-id="${this.props.id}" data-status="${
      this.props.cardStatus
    }" data-index="${this.props.cardIndex}">
          <form action="">
            <input type="text" name="" id="" maxlength="50" placeholder="${this.#CARD_TITLE_PLACEHLODER}"
            value="${this.props.title}" class="card__title" />
            <textarea
              name=""
              id=""
              maxlength="500"
              placeholder="${this.#CARD_CONTENT_PLACEHLODER}"
              class="card__content">${this.props.contents}</textarea>
            <div class="button-wrap card__button-wrap">
              <button type="button" class="button--cancle card__button--cancle">취소</button>
              <button type="button" class="button--${
                this.hasInputValue() ? 'submit' : 'disabled'
              } card__button--submit">등록</button>
            </div>
          </form>
        </li>`;
  }

  normalTemplate() {
    return `<li class="card" data-id="${this.props.id}" data-status="${this.props.cardStatus}" data-index="${this.props.cardIndex}">
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

  checkInputValue($card) {
    const title = this.getTitle($card);
    const contents = this.getContents($card);

    return title && contents ? true : false;
  }

  reRender($card) {
    const changedCard = this.completion ? this.writableTemplate() : this.normalTemplate();
    $card.insertAdjacentHTML('beforebegin', changedCard);
    $card.remove();
  }
}

export { Card };
