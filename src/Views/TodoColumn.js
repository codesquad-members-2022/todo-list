import { v4 as uuidv4 } from 'uuid';
import TodoCard from './TodoCard';
import store from '../store';
import { $, addClass, createElement } from '../utils/utils';

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

  init(cardsData) {
    cardsData.forEach(cardData => {
      const todoCard = new TodoCard(cardData);
      this.todoCards.push(todoCard);
    });

    this.render();
  }

  listen() {
    this.$todoColumn.addEventListener('@clickAddButton', () => {
      if (this.hasActiveCard()) return;
      this.handleClickAddButton();
    });

    this.$todoColumn.addEventListener('@submitNewTodoCard', event => {
      this.handleSubmitNewTodoCard(event);
    });
  }

  addEventHandlers() {}

  // TODO: 타이틀 업데이트

  updateCardCount(cardsData) {
    const $todoItemCount = $('.todo-item-count', this.$todoColumn);
    $todoItemCount.textContent = cardsData.length;
  }

  hasActiveCard() {
    const $activeCard = $('.active-item', this.$todoList);
    return !!$activeCard;
  }

  preRender({ cards: cardsData }) {
    this.todoCards = [];
    cardsData.forEach(cardData => {
      const todoCard = new TodoCard(cardData);
      this.todoCards.push(todoCard);
    });

    this.updateCardCount(cardsData);
    this.render();
  }

  render() {
    const $$todoCard = this.todoCards.map(card => card.$todoCard);
    this.$todoList.textContent = '';
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
    TodoCard.replaceParagraphWithTextarea($todoCard);
    this.$todoList.prepend($todoCard);
  }

  handleSubmitNewTodoCard(event) {
    const {
      detail: { title, desc },
    } = event;

    store.addTodoCard(this.idx, this.id, title, desc, cardsData => {
      this.preRender(cardsData);
    });
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
