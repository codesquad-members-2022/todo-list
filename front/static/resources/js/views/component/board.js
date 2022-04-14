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
      column.disableWriting(column.title);
      this.#deleteNewCardState(column);
    }
  }

  #toggleWritableCard(target, createCard) {
    const $column = this.#setTarget(target, 'column');
    const columnName = $column.dataset.title;
    const column = this.columns[columnName];
    this.#updateColumnState(target, column, createCard);
    column.render(columnName);
  }

  #isNewCard($card) {
    return $card.dataset.id ? true : false;
  }

  #hasInputValue(title, contents) {
    if (title || contents) return true;
    alert('내용을 입력해주세요.');
    return false;
  }

  #createNewCardData($column, $card) {
    const title = $card.getTitle();
    const contents = $card.getContent();
    const columnName = $column.dataset.title;
    const newCardData = {
      cardIndex: $card.dataset.cardIndex,
      title: title,
      contents: contents,
      cardStatus: columnName
    };
    let method = 'PUT';
    const id = $card.dataset.id;

    if (this.#isNewCard($target, newCardData)) {
      cardNewProps.cardIndex = this.columns[columnName].cards.length;
      cardNewProps.writer = $card.dataset.writer;
      method = 'POST';
    }

    return this.#hasInputValue(title, contents) ? { newCardData, method, id } : null;
  }

  #sendNewCardData(target, $card, observe) {
    const $column = this.#setTarget(target, 'column');
    const { newCardData, method, id } = this.#createNewCardData($column, $card);
    if (newCardData) observe(newCardData, method, id);
  }

  #cardClickEventHandler(target, [createCard, observe, showPopup]) {
    const $card = this.#setTarget(target, 'card');
    if (this.#isfindedTarget(target, 'card-button--add')) this.#toggleWritableCard(target, createCard);
    else if (this.#isfindedTarget(target, 'card__button--cancle') && !$card.dataset.id)
      this.#toggleWritableCard(target, createCard);
    else if (this.#isfindedTarget(target, 'card__button--submit')) this.#sendNewCardData(target, $card, observe);
    else if (this.#isfindedTarget(target, 'card__button--delete')) showPopup($card.dataset.id);
  }

  #addCardClickEvent([createCard, observe, showPopup]) {
    document
      .querySelector('.column-wrap')
      .addEventListener('click', ({ target }) => this.#cardClickEventHandler(target, [createCard, observe, showPopup]));
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
    this.#addCardInputEvent();
  }
}

export { Board };
