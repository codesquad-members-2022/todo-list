class Column {
  #title;
  #cards;

  constructor({ title, cards }) {
    this.#title = title;
    this.#cards = cards;
  }

  #isDisabledBtn(columnName) {
    const $column = document.querySelector(`.column[data-title=${columnName}}`);
    const isDisabled = $column.querySelector('.button--add').getAttribute('disabled');
    return isDisabled ? true : false;
  }

  #activateBtnToAddCard($addBtn) {
    $addBtn.removeAttribute('disabled');
  }

  disableWriting(columnName) {
    const isDisabledBtn = this.#isDisabledBtn(columnName);
    if (isDisabledBtn) this.#activateBtnToAddCard($addBtn);
  }

  disableAddBtn($addBtn) {
    $addBtn.setAttribute('disabled', true);
  }

  #getCardsTemplate() {
    const cardsTemplate = this.#cards.reduce((cardsTemplate, card) => {
      const cardTemplate = card.template();
      cardsTemplate = cardTemplate + cardsTemplate;

      return cardsTemplate;
    }, '');

    return cardsTemplate;
  }

  template() {
    return `<div class="column" data-title="${this.#title}">
            <div class="column__top-wrap">
              <div class="column__title-wrap">
                <h2 class="column__title">${this.#title}</h2>
                <span class="card-num">${this.#cards.length}</span>
              </div>
              <div class="column__button-wrap">
                <button type="button" class="button--add card-button--add" data-status="${this.#title}">
                  <span class="hidden-text">카드 추가 버튼</span>
                </button>
                <button type="button" class="button--delete column__button--delete">
                  <span class="hidden-text">컬럼 삭제 버튼</span>
                </button>
              </div>
            </div>
            <ul class="cards">
            ${this.#getCardsTemplate()}
            </ul>
          </div>`;
  }

  render(columnName) {
    const cardsTemplate = this.#getCardsTemplate();
    const $column = document.querySelector(`.column[data-title=${columnName}}`);
    const $cards = $column.querySelector('.cards');
    $cards.innerHTML = cardsTemplate;
  }
}

export { Column };
