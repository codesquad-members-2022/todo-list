class Board {
  render(columns) {
    document.querySelector('main').insertAdjacentHTML('afterbegin', this.template(columns));
  }

  template(columns) {
    return `<div class="column-wrap">
        ${columns}
      </div>`;
  }

  addEvent(_this) {
    document.querySelector('main').addEventListener('click', _this.btnClickHandler.bind(_this));
  }
}

export { Board };
