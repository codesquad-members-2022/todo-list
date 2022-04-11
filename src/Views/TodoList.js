import { v4 as uuidv4 } from 'uuid';
import { TodoItem } from './TodoItem.js';
import { createElement, $ } from '../utils/utils.js';

export class TodoList {
  constructor(id = uuidv4(), title, cards) {
    this.$element;
    this.id = id;
    this.title = title;
    this.cards = [];
  }

  init(columnData) {
    const { cards, id, title } = columnData;
    this.id = id;
    this.title = title;

    cards.forEach(cardData => {
      const $todoItem = new TodoItem(null);
      $todoItem.init(cardData);
      this.cards.push($todoItem);
    });

    this.$element = createTodoList(id, title, this.cards);
    const $todoList = $('.todo-list', this.$element);
    const $$todoItem = this.cards.map((card) => {
      return card.$element
    })

    $todoList.append(...$$todoItem);

  }
}

const createTodoList = (id, title, cards) => {
  const $todoList = createElement('div', 'todo-column', {
    'data-id': id,
  });

  $todoList.innerHTML = `
    <header class="todo-column-header">
      <div class="todo-column-textarea">
        <h1 class="todo-column-title">${title}</h1>
        <div class="todo-item-count badge-gray4">${cards.length}</div>
      </div>
      <div class="todo-column-buttons">
        <button type="button" class="add-button">
          <i class="ic-plus"></i>
        </button>
        <button type="button" class="delete-button">
          <i class="ic-close"></i>
        </button>
      </div>
    </header>
    <div class="todo-column-body">
      <ol class="todo-list">   
      </ol>
    </div>
  `;

  return $todoList;
};
