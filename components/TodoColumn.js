import TodoInput from './TodoInput.js';
import { $ } from '../utils/dom.js';

export default class TodoColumn {
  constructor(status) {
    this.status = status;
    this.todoInput = new TodoInput(this.status, this.setOnInput, this.handleAddCount);
    this.onInput = false;
    this.count = 0;
  }

  setOnInput = onInput => {
    this.onInput = onInput;
  };

  onAddClick = () => {
    if (this.onInput) {
      $(`.input-${this.status}`)?.remove();
      this.setOnInput(false);
      return;
    }

    $(`.${this.status}`).insertAdjacentHTML('afterend', this.todoInput.render());
    this.todoInput.handleEventListener();
    this.setOnInput(true);
    return;
  };

  handleAddCount = () => {
    this.onAddCount();
    this.renderCount();
  };

  handleMinusCount = () => {
    this.onMinusCount();
    this.renderCount();
  };

  setCount = count => {
    this.count = count;
  };

  onAddCount = () => {
    this.count++;
  };

  onMinusCount = () => {
    this.count--;
  };

  renderCount = () => {
    $(`.${this.status} .column__count`).innerText = this.count;
  };

  handleEventListener = () => {
    $(`.${this.status} .column__add`).addEventListener('click', this.onAddClick);
  };

  render = () => {
    return /* html */ `
    <article class="column-list ${this.status}-wrapper" data-status="${this.status}">
        <nav class="column ${this.status}">
            <div class="column__left">
                <span class="column__title">${this.status}</span>
                <div class="column__count">${this.count}</div>
            </div>
            <div class="column__right">
            <button class="column__add">+</button>
            <button class="column__delete">x</button>
            </div>
        </nav>
        
    </article>
      `;
  };
}
