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

  render() {
    document.querySelector('.column-wrap').innerHTML = this.#getColumnsTemplate();
  }

  #isfindedTarget(target, targetName) {
    return target.classList.contains(targetName) || target.closest('.' + targetName) ? true : false;
  }

  #setTarget(target, targetName) {
    const $target = target.classList.contains(targetName) ? target : target.closest('.' + targetName);
    return $target;
  }

  #addNewCardState($column, createCard) {
    const columnName = $column.dataset.title;
    const column = this.columns[columnName];
    column.cards = [...column.cards, createCard({ cardStatus: column.title, cardIndex: column.cards.length }, false)];
    column.hasWritableCard = true;
  }

  #deleteNewCardState($column) {
    const columnName = $column.dataset.title;
    const column = this.columns[columnName];
    column.cards = column.cards.slice(0, -1);
    column.hasWritableCard = false;
  }

  #renderColumn($column) {
    const columnName = $column.dataset.title;
    const column = this.columns[columnName];
    column.reRender($column);
  }

  #isNewCard($card) {
    return $card.dataset.id === 'undefined' ? true : false;
  }

  #getInputValue($card) {
    const selectedCard = this.columns[$card.dataset.status].cards[$card.dataset.index];
    const title = selectedCard.getTitle($card);
    const contents = selectedCard.getContents($card);

    return { title, contents };
  }

  #createNewCardData($card, $column) {
    const { title, contents } = this.#getInputValue($card);
    if (!title || !contents) return;

    const cardData = {
      cardIndex: $card.dataset.index,
      title: title,
      contents: contents,
      cardStatus: $column.dataset.title,
      writer: '도니'
    };
    const method = 'POST';

    return { method, cardData };
  }

  #createModifiedCardData($card, $column) {
    const { title, contents } = this.#getInputValue($card);
    if (!title || !contents) return;

    const cardData = {
      cardIndex: $card.dataset.index,
      title: title,
      contents: contents,
      cardStatus: $column.dataset.title
    };
    const method = 'PUT';
    const id = $card.dataset.id;

    return { method, cardData, id };
  }

  #sendCardData($card, $column, observe) {
    const { method, cardData, id } = this.#isNewCard($card)
      ? this.#createNewCardData($card, $column)
      : this.#createModifiedCardData($card, $column);
    observe(method, cardData, id);
  }

  #reRenderSelectedCard($card, completion) {
    const selectedColumn = this.columns[$card.dataset.status];
    const selectedCard = selectedColumn.cards[$card.dataset.index];
    selectedCard.completion = completion;
    selectedCard.reRender($card);
  }

  #cardClickEventHandler(target, [createCard, observe, showPopup]) {
    const $card = this.#setTarget(target, 'card');
    const $column = this.#setTarget(target, 'column');
    switch (true) {
      case this.#isfindedTarget(target, 'card-add-button'):
        this.#addNewCardState($column, createCard);
        this.#renderColumn($column);
        break;
      case this.#isfindedTarget(target, 'card__button--cancle'):
        if (this.#isNewCard($card)) {
          this.#deleteNewCardState($column);
          this.#renderColumn($column);
        } else this.#reRenderSelectedCard($card, true);
        break;
      case this.#isfindedTarget(target, 'card__button--submit'):
        this.#sendCardData($card, $column, observe);
        break;
      case this.#isfindedTarget(target, 'card__button--delete'):
        showPopup($card.dataset.id);
    }
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
      const card = this.columns[$card.dataset.status].cards[$card.dataset.index];
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
