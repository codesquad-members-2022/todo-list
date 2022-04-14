class Card {
  #CARD_TITLE_PLACEHLODER = '제목을 입력하세요';
  #CARD_CONTENT_PLACEHLODER = '내용을 입력하세요';

  constructor(props) {
    this.props = props;
  }

  getFormElement(columnName) {
    return document.querySelector(`form[data-status=${columnName}]`);
  }

  getTitle($form) {
    return $form.querySelector('.card__title').value;
  }

  getContent($form) {
    return $form.querySelector('.card__content').value;
  }

  hasTitleValue() {
    return this.props.title ? true : false;
  }

  hasContentsValue() {
    return this.props.contents ? true : false;
  }

  hasInputValue() {
    return this.props.title && this.props.contents ? true : false;
  }

  getSubmitBtn(columnName, currentBtnClassName) {
    const $form = this.getFormElement(columnName);
    const $submitBtn = $form.querySelector(`.${currentBtnClassName}`);
    return $submitBtn;
  }

  activateSubmitBtn($submitBtn) {
    if ($submitBtn) {
      $submitBtn.classList.replace('button--disabled', 'button--submit');
      $submitBtn.removeAttribute('disabled');
    }
  }

  disableSubmitBtn($submitBtn) {
    if ($submitBtn) {
      $submitBtn.classList.replace('button--submit', 'button--disabled');
      $submitBtn.setAttribute('disabled', true);
    }
  }

  controllSubmitBtn(columnName, currentBtnClassName) {
    const $submitBtn = this.getSubmitBtn(columnName, currentBtnClassName);
    if ($submitBtn && $submitBtn.classList.contains('button--submit')) {
      this.disableSubmitBtn($submitBtn);
    } else {
      this.activateSubmitBtn($submitBtn);
    }
  }

  writableTemplate() {
    return `<li class="card card--write" data-id="${this.props.id}" data-status="${
      this.props.cardStatus
    }" data-index="${this.props.cardIndex}">
          <form name="writable-form" data-status="${this.props.cardStatus}">
            <input type="text" name="writable-form" maxlength="50" placeholder="${
              this.#CARD_TITLE_PLACEHLODER
            }" value="${this.hasTitleValue() ? this.props.title : ''}" class="card__title" />
            <textarea
              name="writable-form"
              maxlength="500"
              placeholder="${this.#CARD_CONTENT_PLACEHLODER}"
              class="card__content"
            >${this.hasContentsValue() ? this.props.contents : ''}</textarea>
            <span class="card__writer">도니</span>
            <div class="button-wrap card__button-wrap">
              <button type="button" class="button--cancle card__button--cancle" data-status="${
                this.props.cardStatus
              }">취소</button>
              <button type="button" class="button--${
                this.hasInputValue() ? 'submit' : 'disabled'
              } card__button--submit" data-status="${this.props.cardStatus}" disabled>등록</button>
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
