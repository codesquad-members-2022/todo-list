class Column {
  constructor(props) {
    this.props = props;
  }

  getColumnElement(columnName) {
    return document.querySelector(`.column[data-title=${columnName}]`);
  }

  toggleDisableAttribute(columnName) {
    const $column = this.getColumnElement(columnName);
    const $addBtn = $column.querySelector('.button--add');
    const isDisabled = $addBtn.getAttribute('disabled');
    if (isDisabled) {
      $addBtn.removeAttribute('disabled');
    }
  }

  render(columnName, cards) {
    const $column = this.getColumnElement(columnName);
    const $cards = $column.querySelector('.cards');
    $cards.innerHTML = cards;
  }

  template(cards) {
    return `<div class="column" data-title="${this.props.title}">
            <div class="column__top-wrap">
              <div class="column__title-wrap">
                <h2 class="column__title">${this.props.title}</h2>
                <span class="card-num">${this.props.cards.length}</span>
              </div>
              <div class="column__button-wrap">
                <button type="button" class="button--add card-button--add" data-status="${this.props.title}">
                  <span class="hidden-text">카드 추가 버튼</span>
                </button>
                <button type="button" class="button--delete column__button--delete">
                  <span class="hidden-text">컬럼 삭제 버튼</span>
                </button>
              </div>
            </div>
            <ul class="cards">
            ${cards}
            </ul>
          </div>`;
  }
}

export { Column };
