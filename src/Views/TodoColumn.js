import { v4 as uuidv4 } from 'uuid';
import TodoCard from './TodoCard';
import { createElement, $, addClass } from '../utils/utils';

export default class TodoColumn {
  constructor({ cards: cardsData, id, idx, title }) {
    this.id = id;
    this.idx = idx;
    this.title = title;
    this.todoCards = [];
    this.$todoColumn = createTodoColumn(id, title, cardsData);
    this.$todoList = $('.todo-list', this.$todoColumn);
    this.init(cardsData);
    this.listen();
  }

  listen() {
    this.$todoColumn.addEventListener('@clickAddButton', () => {
      if (this.hasActiveCard()) return;
      this.handleClickAddButton();
    });
  }

  addEventHandlers() {}

  init(cardsData) {
    cardsData.forEach(cardData => {
      const todoCard = new TodoCard(cardData);
      this.todoCards.push(todoCard);
    });

    this.render();
  }
  // 타이틀 업데이트

  // 배지 업데이트

  hasActiveCard() {
    const $activeCard = $('.active-item', this.$todoList);
    return !!$activeCard;
  }

  render() {
    const $$todoCard = this.todoCards.map(card => card.$todoCard);
    this.$todoList.append(...$$todoCard);
  }

  /* event handler */
  handleClickAddButton() {
    const { $todoCard } = new TodoCard({
      id: uuidv4(),
      columnId: this.id,
      columnIdx: this.idx,
    });
    addClass('active-item', $todoCard);
    this.$todoList.prepend($todoCard); // 모습이나타남
  }
}

const createTodoColumn = (id, title, cardsData) => {
  const $todoColumn = createElement('div', 'todo-column', {
    'data-id': id,
  });

  $todoColumn.innerHTML = `
    <header class="todo-column-header">
      <div class="todo-column-textarea">
        <h1 class="todo-column-title">${title}</h1>
        <div class="todo-item-count badge-gray4">${cardsData.length}</div>
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
