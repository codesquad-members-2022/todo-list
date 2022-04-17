import { getLocalStorageByKey } from '../utils/localStorage.js';
import TodoInput from './TodoInput.js';
import { $ } from '../utils/dom.js';

export default class TodoColumn {
  constructor(status) {
    this.status = status;
    this.modifyStatus = '';
    this.todoInput = new TodoInput(this.status, this.setOnInput, this.handleAddCount, this.handleMinusCount);
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

  onDeleteClick = () => {
    const columnStatus = $(`.${this.status}-wrapper`).dataset.status;

    const filteredColumn = getLocalStorageByKey('column').filter(e => e !== columnStatus);
    const filteredTodos = getLocalStorageByKey('todos').filter(todo => todo.status !== columnStatus);

    localStorage.setItem('todos', JSON.stringify(filteredTodos));
    localStorage.setItem('column', JSON.stringify(filteredColumn));
    $(`.${this.status}-wrapper`)?.remove();
  };

  onEditClick = () => {
    $(`.${this.status} .column__left`).innerHTML = `<input type="text" class="editedTitle">`;
    $(`.${this.status} .editedTitle`).addEventListener('input', this.onEditInput);
  };

  onEditTitle = ({ target }) => {
    if (target.classList.contains('editedTitle')) {
      return;
    }

    if (!this.modifyStatus) return;

    // data update
    // view update
    $(`.${this.status} .column__left`).innerHTML = `<span class="column__title">${this.modifyStatus}</span>`;
    this.status = this.modifyStatus;
  };

  onEditInput = e => {
    this.modifyStatus = e.target.value;
  };

  handleEventListener = () => {
    $(`.${this.status} .column__title`).addEventListener('dblclick', this.onEditClick);
    $(`.${this.status} .column__add`).addEventListener('click', this.onAddClick);
    $(`.${this.status} .column__delete`).addEventListener('click', this.onDeleteClick);
    document.body.addEventListener('click', this.onEditTitle);
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
