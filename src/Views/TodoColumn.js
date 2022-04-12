import TodoCard from './TodoCard';
import { createElement, $ } from '../utils/utils';

export default class TodoColumn {
  constructor({ cards: cardsData, id, title }) {
    this.$todoColumn = null;
    this.$todoList = null;
    this.id = id;
    this.title = title;
    this.todoCards = [];
    this.init(cardsData);
  }

  init(cardsData) {
    cardsData.forEach(cardData => {
      const todoCard = new TodoCard(cardData);
      this.todoCards.push(todoCard);
    });

    this.$todoColumn = createTodoColumn(this.id, this.title, this.todoCards);
    this.$todoList = $('.todo-list', this.$todoColumn);
    this.render();
  }
  // 타이틀 업데이트

  // 배지 업데이트

  render() {
    const $$todoCard = this.todoCards.map(card => {
      return card.$todoCard;
    });

    this.$todoList.append(...$$todoCard);
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
