class Column {
  constructor({ title, cards }) {
    this.title = title;
    this.cards = cards;
    this.hasWritableCard = false;
  }

  #getAddColumnBtn() {
    const $column = document.querySelector(`.column[data-title=${this.title}]`);
    const $addBtn = $column.querySelector('.card-add-button');
    return $addBtn;
  }

  #activateAddColumnBtn() {
    const $addBtn = this.#getAddColumnBtn();
    $addBtn.removeAttribute('disabled');
  }

  #disableAddColumnBtn() {
    const $addBtn = this.#getAddColumnBtn();
    $addBtn.setAttribute('disabled', true);
  }

  #getCardsTemplate() {
    const cardsTemplate = this.cards.reduce((cardsTemplate, card) => {
      const cardTemplate = card.template();
      cardsTemplate = cardTemplate + cardsTemplate;

      return cardsTemplate;
    }, '');

    return cardsTemplate;
  }

  template() {
    return `<div class="column" data-title="${this.title}">
            <div class="column__top-wrap">
              <div class="column__title-wrap">
                <h2 class="column__title">${this.title}</h2>
                <span class="card-num">${this.cards.length}</span>
              </div>
              <div class="column__button-wrap">
                <button type="button" class="button--add card-add-button" data-status="${this.title}">
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

  reRender($column) {
    console.log(this.hasWritableCard);
    const cardsTemplate = this.#getCardsTemplate();
    const $cards = $column.querySelector('.cards');
    $cards.innerHTML = cardsTemplate;
    this.hasWritableCard ? this.#disableAddColumnBtn() : this.#activateAddColumnBtn();
  }
}

export { Column };
