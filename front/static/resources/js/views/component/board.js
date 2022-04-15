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

  #addNewCardState(column, createCard) {
    column.cards = [...column.cards, createCard({ cardStatus: column.title }, false)];
  }

  #deleteNewCardState(column) {
    column.cards = column.cards.slice(0, -1);
  }

  #updateColumnState(target, column, createCard) {
    if (target.classList.contains('button--add')) {
      column.disableAddBtn(target);
      this.#addNewCardState(column, createCard);
    } else if (target.classList.contains('button--cancle')) {
      column.disableWriting(target, column.title);
      this.#deleteNewCardState(column);
    }
  }

  #toggleWritableCard(target, createCard) {
    const $column = this.#setTarget(target, 'column');
    const columnName = $column.dataset.title;
    const column = this.columns[columnName];
    this.#updateColumnState(target, column, createCard);
    column.render($column);
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
      cardIndex: $card.dataset.index,
      title: title,
      contents: contents,
      cardStatus: columnName
    };
    let method = 'PUT';
    const id = $card.dataset.id;

    if (this.#isNewCard($card, newCardData)) {
      console.log('a');
      newCardData.cardIndex = this.columns[columnName].cards.length;
      newCardData.writer = '도니';
      method = 'POST';
    }
    return this.#hasInputValue(title, contents) ? { newCardData, method, id } : null;
  }

  #sendNewCardData(target, $card, observe) {
    const $column = this.#setTarget(target, 'column');
    const { newCardData, method, id } = this.#createNewCardData($column, $card);
    if (newCardData) observe(newCardData, method, id);
  }

  #reRenderSelectedCard($card, completion) {
    const selectedColumn = this.columns[$card.dataset.status];
    const selectedCard = selectedColumn.cards[$card.dataset.index ? $card.dataset.index : 0];
    selectedCard.completion = completion;
    selectedCard.reRender($card);
  }

  #cardClickEventHandler(target, [createCard, observe, showPopup]) {
    const $card = this.#setTarget(target, 'card');
    if (this.#isfindedTarget(target, 'card-button--add')) this.#toggleWritableCard(target, createCard);
    else if (this.#isfindedTarget(target, 'card__button--cancle') && $card.dataset.id !== 'undefined') {
      this.#reRenderSelectedCard($card, true);
    } else if (this.#isfindedTarget(target, 'card__button--cancle') && $card.dataset.id === 'undefined')
      this.#toggleWritableCard(target, createCard);
    else if (this.#isfindedTarget(target, 'card__button--submit')) this.#sendNewCardData(target, $card, observe);
    else if (this.#isfindedTarget(target, 'card__button--delete')) showPopup($card.dataset.id);
  }

  #addCardClickEvent([createCard, observe, showPopup]) {
    document
      .querySelector('.column-wrap')
      .addEventListener('click', ({ target }) => this.#cardClickEventHandler(target, [createCard, observe, showPopup]));
  }

  #addCardDblClickEvent() {
    document.querySelector('.column-wrap').addEventListener('dblclick', ({ target }) => {
      const $card = this.#setTarget(target, 'card');
      if (this.#isfindedTarget(target, 'card')) this.#reRenderSelectedCard($card, false);
    });
  }

  #addCardInputEvent() {
    document.querySelector('.column-wrap').addEventListener('input', ({ target }) => {
      const $card = this.#setTarget(target, 'card');
      const card = this.columns[$card.dataset.cardeStatus][$card.dataset.cardIndex];
      card.toggleSubmitButton($card);
    });
  }

  addEvent([createCard, observe, showPopup]) {
    this.#addCardClickEvent([createCard, observe, showPopup]);
    this.#addCardDblClickEvent();
    this.#addCardInputEvent();
  }
}

export { Board };
