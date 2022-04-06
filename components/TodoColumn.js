import TodoInput from './TodoInput.js';
export default class TodoColumn {
  constructor(title) {
    this.target = document.querySelector('.column-section');
    this.title = title;
    this.onInput = false;
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
    if (this.onInput) {
      document.querySelector(`.input-${this.title}`)?.remove();
      this.onInput = false;
      return;
    }

    if (target.classList.contains('column__add')) {
      const newInput = new TodoInput(this.title);
      document.querySelector(`.${this.title}`).insertAdjacentHTML('afterend', newInput.template());
      document.querySelector(`.input-${this.title} .input--cancel`).addEventListener('click', this.onCloseBtn);
      document.querySelector(`.input-${this.title} .input-content`).addEventListener('input', this.onInputContent);
      this.onInput = true;
      return;
    }
  };

  onInputContent = event => {
    if (event.target.value.length === 0) {
      document.querySelector(`.input-${this.title} .input--register`).disabled = true;
      document.querySelector(`.input-${this.title} .input--register`).style.background = '#86c6ff';
    } else {
      document.querySelector(`.input-${this.title} .input--register`).disabled = false;
      document.querySelector(`.input-${this.title} .input--register`).style.background = '#0075DE';
    }
  };

  onCloseBtn = () => {
    document.querySelector(`.input-${this.title}`)?.remove();
    this.onInput = false;
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
