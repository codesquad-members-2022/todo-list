class Column {
  constructor(props) {
    this.props = props;
  }

  template(cards) {
    return `<div class="column">
            <div class="column__top-wrap">
              <div class="column__title-wrap">
                <h2 class="column__title">${this.props.title}</h2>
                <span class="card-num">${this.props.cards.length}</span>
              </div>
              <div class="column__button-wrap">
                <button type="button" class="button--add card-button--add">
                  <span class="hidden-text">컬럼 추가 버튼</span>
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
