import TodoCard from './TodoCard';
import { createElement, $ } from '../utils/utils';

export default class TodoColumn {
  constructor(id = null, title = null, cards = []) {
    this.$todoColumn = null;
    this.id = id;
    this.title = title;
    this.cards = cards;
  }

  init(columnData) {
    const { cards: cardsData, id, title } = columnData;
    this.id = id;
    this.title = title;

    cardsData.forEach(cardData => {
      const todoCard = new TodoCard(null);
      todoCard.init(cardData);
      this.cards.push(todoCard);
    });

    this.$todoColumn = createTodoColumn(id, title, this.cards);
    const $todoList = $('.todo-list', this.$todoColumn);
    const $$todoCard = this.cards.map(card => {
      return card.$todoCard;
    });

    $todoList.append(...$$todoCard);
  }
}

const createTodoColumn = (id, title, cards) => {
  const $todoColumn = createElement('div', 'todo-column', {
    'data-id': id,
  });

  $todoColumn.innerHTML = `
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

  return $todoColumn;
};
