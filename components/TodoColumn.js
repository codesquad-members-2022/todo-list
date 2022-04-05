import TodoInput from './TodoInput.js';
export default class TodoColumn {
  constructor(title) {
    this.target = document.querySelector('.column-section');
    this.title = title;
    this.onInput = true;
    this.render();
  }

  onMouseOver = ({ target }) => {
    if (target.classList.contains('column__add')) {
      if (!target.classList.contains('sky-blue')) {
        target.classList.add('sky-blue');
      }
    }
  };

  onMouseOut = ({ target }) => {
    if (target.classList.contains('column__add')) {
      if (target.classList.contains('sky-blue')) {
        target.classList.remove('sky-blue');
      }
    }
  };

  onAddClick = ({ target }) => {
    if (!this.onInput) {
      document.querySelector(`.input-${this.title}`)?.remove();
      this.onInput = true;
      return;
    }

    if (target.classList.contains('column__add')) {
      const newInput = new TodoInput(this.title);
      document.querySelector(`.${this.title}`).insertAdjacentHTML('afterend', newInput.template());
      this.onInput = false;
      return;
    }
  };

  render = () => {
    const columnListHTML = /* html */ `
    <article class="column-list">
        <nav class="column ${this.title}">
            <div class="column__left">
                <span class="column__title">${this.title}</span>
                <div class="column__count">0</div>
            </div>
            <div class="column__right">
            <div class="column__add">+</div>
            <div class="column__delete">x</div>
            </div>
        </nav>
    </article>
      `;
    this.target.insertAdjacentHTML('beforeend', columnListHTML);
    // 이슈사항 정리 예정
    document.querySelector(`.${this.title}`).addEventListener('mouseover', this.onMouseOver);
    document.querySelector(`.${this.title}`).addEventListener('mouseout', this.onMouseOut);
    document.querySelector(`.${this.title}`).addEventListener('click', this.onAddClick);
  };
}
