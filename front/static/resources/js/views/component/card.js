class Card {
  #CARD_TITLE_PLACEHLODER = '제목을 입력하세요';
  #CARD_CONTENT_PLACEHLODER = '내용을 입력하세요';

  constructor({ id, cardIndex, title, contents, writer, creatTime, cardStatus }, completion = true) {
    this.id = id;
    this.cardIndex = cardIndex;
    this.title = title;
    this.contents = contents;
    this.writer = writer;
    this.creatTime = creatTime;
    this.cardeStatus = cardStatus;
    this.completion = completion;
  }

  getTitle($card) {
    return $card.querySelector('.card__title').value;
  }

  getContent($card) {
    return $card.querySelector('.card__content').value;
  }

  #activateSubmitBtn($submitBtn) {
    $submitBtn.classList.replace('button--disabled', 'button--submit');
    $submitBtn.removeAttribute('disabled');
  }

  #disableSubmitBtn($submitBtn) {
    $submitBtn.classList.replace('button--submit', 'button--disabled');
    $submitBtn.setAttribute('disabled', true);
  }

  toggleSubmitButton($card) {
    if (this.getTitle($card) && this.getContent($card)) this.#activateSubmitBtn();
    else this.#disableSubmitBtn();
  }

  template() {
    const writableCard = `<li class="card card--write" data-id="${this.id}" data-status="${
      this.cardStatus
    }" data-index="${this.cardIndex}">
          <form name="writable-form" data-status="${this.cardStatus}">
            <input type="text" name="writable-form" maxlength="50" placeholder="${
              this.#CARD_TITLE_PLACEHLODER
            }" value="${this.title ? this.title : ''}" class="card__title" />
            <textarea
              name="writable-form"
              maxlength="500"
              placeholder="${this.#CARD_CONTENT_PLACEHLODER}"
              class="card__content"
            >${this.contents ? this.contents : ''}</textarea>
            <span class="card__writer">도니</span>
            <div class="button-wrap card__button-wrap">
              <button type="button" class="button--cancle card__button--cancle" data-status="${
                this.cardStatus
              }">취소</button>
              <button type="button" class="button--${
                this.title && this.contents ? 'submit' : 'disabled'
              } card__button--submit" data-status="${this.cardStatus}" disabled>등록</button>
            </div>
          </form>
        </li>`;
    const normalCard = `<li class="card" data-id="${this.id}" data-status="${this.cardStatus}" data-index="${this.cardIndex}>
          <h3 class="card__title">${this.title}</h3>
          <div class="card__content">
            <p>${this.contents}</p>
          </div>
          <p class="card__writer">${this.writer}</p>
          <button type="button" class="button--delete card__button--delete">
            <span class="hidden-text">카드 삭제 버튼</span>
          </button>
        </li>`;

    return this.completion ? normalCard : writableCard;
  }
}

export { Card };
