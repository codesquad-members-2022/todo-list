import TodoInput from './TodoInput.js';

export default class TodoColumn {
  constructor(status) {
    this.parentTarget = document.querySelector('.column-section');
    this.status = status;
    this.todoInput = new TodoInput(this.status, this.handleCount);
    this.onInput = false;
    this.count = 0;
  }

  onAddClick = () => {
    if (this.onInput) {
      document.querySelector(`.input-${this.status}`)?.remove();
      this.onInput = false;
      return;
    }

    document.querySelector(`.${this.status}`).insertAdjacentHTML('afterend', this.todoInput.render());
    this.todoInput.run();
    this.onInput = true;
    return;
  };

  handleCount = () => {
    this.onAddCount();
    this.renderCount();
  };

  onAddCount = () => {
    this.count++;
  };

  renderCount = () => {
    document.querySelector(`.${this.status} .column__count`).innerText = this.count;
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
    document.querySelector(`.${this.status} .column__add`).addEventListener('click', this.onAddClick);
  };
}
