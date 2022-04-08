import TodoInput from './TodoInput.js';
export default class TodoColumn {
  constructor(status) {
    this.parentTarget = document.querySelector('.column-section');
    this.status = status;
    this.todoInput = new TodoInput(this.status);
    this.onInput = false;
  }

  onAddClick = ({ target }) => {
    if (this.onInput) {
      document.querySelector(`.input-${this.status}`)?.remove();
      this.onInput = false;
      return;
    }

    if (target.classList.contains('column__add')) {
      document.querySelector(`.${this.status}`).insertAdjacentHTML('afterend', this.todoInput.render());
      this.todoInput.run();
      this.onInput = true;
      return;
    }
  };

  render = () => {
    const columnListHTML = /* html */ `
    <article class="column-list">
        <nav class="column ${this.status}">
            <div class="column__left">
                <span class="column__title">${this.status}</span>
                <div class="column__count">0</div>
            </div>
            <div class="column__right">
            <button class="column__add">+</button>
            <button class="column__delete">x</button>
            </div>
        </nav>
        
    </article>
      `;
    this.parentTarget.insertAdjacentHTML('beforeend', columnListHTML);
    document.querySelector(`.${this.status}`).addEventListener('click', this.onAddClick);
  };
}
