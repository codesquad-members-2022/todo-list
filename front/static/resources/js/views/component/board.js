class Board {
  constructor() {
    this.columns = {};
  }

  setColumns(columns) {
    this.columns = columns;
  }

  #getColumnsTemplate() {
    const columnsTemplate = Object.keys(this.columns).reduce((columnsTemplate, columnName) => {
      const column = this.columns[columnName];
      columnsTemplate += column.template();
      return columnsTemplate;
    }, '');

    return columnsTemplate;
  }

  template() {
    return `<div class="column-wrap">
        ${this.#getColumnsTemplate()}
      </div>`;
  }

  render() {
    document.querySelector('main').insertAdjacentHTML('afterbegin', this.template());
  }

  #isfindedTarget(target, targetName) {
    return target.classList.contains(targetName) || target.closest('.' + targetName) ? true : false;
  }

  #setTarget(target, targetName) {
    const $target = target.classList.contains(targetName) ? target : target.closest('.' + targetName);
    return $target;
  }

  #isNewCard($card) {
    return $card.dataset.id ? false : true;
  }

  #hasInputValue(title, contents) {
    if (title || contents) return true;
    alert('내용을 입력해주세요.');
    return false;
  }

  #createNewCardData($column, $card) {
    const selectedCard = this.columns[$card.dataset.status].cards[$card.dataset.index];
    const title = selectedCard.getTitle($card);
    const contents = selectedCard.getContents($card);
    const columnName = $column.dataset.title;
    const newCardData = {
      cardIndex: $card.dataset.cardIndex,
      title: title,
      contents: contents,
      cardStatus: columnName
    };
    let method = 'PUT';
    const id = $card.dataset.id;

    if (this.#isNewCard($card, newCardData)) {
      newCardData.cardIndex = this.columns[columnName].cards.length;
      newCardData.writer = $card.dataset.writer;
      method = 'POST';
    }
    return this.#hasInputValue(title, contents) ? { newCardData, method, id } : null;
  }

  #sendNewCardData(target, $card, observe) {
    const $column = this.#setTarget(target, 'column');
    const { newCardData, method, id } = this.#createNewCardData($column, $card);
    if (newCardData) observe(newCardData, method, id);
  }

  #reRenderSelectedCard($card) {
    const selectedCard = this.columns[$card.dataset.status].cards[$card.dataset.index];
    selectedCard.reRender($card);
    selectedCard.completion = selectedCard.completion ? false : true;
  }

  #addCardClickEvent(observe) {
    document.querySelector('.column-wrap').addEventListener('click', ({ target }) => {
      const $card = this.#setTarget(target, 'card');
      if (this.#isfindedTarget(target, 'card__button--cancle') && $card.dataset.id) this.#reRenderSelectedCard($card);
      else if (this.#isfindedTarget(target, 'card__button--submit')) this.#sendNewCardData(target, $card, observe);
    });
  }

  #addCardDblClickEvent() {
    document.querySelector('.column-wrap').addEventListener('dblclick', ({ target }) => {
      const $card = this.#setTarget(target, 'card');
      if (this.#isfindedTarget(target, 'card')) this.#reRenderSelectedCard($card);
    });
  }

  addEvent([observe]) {
    this.#addCardClickEvent(observe);
    this.#addCardDblClickEvent();
  }
}

export { Board };
