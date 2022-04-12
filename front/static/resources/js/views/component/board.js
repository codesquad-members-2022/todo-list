class Board {
  constructor(props) {
    this.props = props;
  }

  render(columns) {
    document.querySelector('main').insertAdjacentHTML('afterbegin', this.template(columns));
  }

  template(columns) {
    return `<div class="column-wrap">
        ${columns}
      </div>`;
  }
}

export { Board };
