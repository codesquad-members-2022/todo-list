import { eventDelegate, emit } from '../utils/utils';
import TodoColumn from './TodoColumn';

export default class TodoColumns {
  constructor($todoColumns) {
    this.$todoColumns = $todoColumns;
    this.todoColumns = [];
    this.addEventHandlers();
  }

  addEventHandlers() {
    eventDelegate({
      target: this.$todoColumns,
      eventName: 'click',
      selector: '.add-button',
      handler: event => {
        this.handleClickAddButton(event);
      },
    });

    eventDelegate({
      target: this.$todoColumns,
      eventName: 'click',
      selector: '.todo-buttons .cancel-button',
      handler: event => {
        this.handleClickCancelButton(event);
      },
    });

    eventDelegate({
      target: this.$todoColumns,
      eventName: 'input',
      selector: '.todo-form textarea',
      handler: event => {
        this.handleInputTextarea(event);
      },
    });

    eventDelegate({
      target: this.$todoColumns,
      eventName: 'click',
      selector: '.todo-item .delete-button',
      handler: event => {
        this.handleClickDeleteCardButton(event);
      },
    });
  }

  init(columnsData) {
    columnsData.forEach(columnData => {
      const todoColumn = new TodoColumn(columnData);
      this.todoColumns.push(todoColumn);
    });

    this.render();
  }

  render() {
    const $$todoColumn = this.todoColumns.map(column => column.$todoColumn);
    this.$todoColumns.append(...$$todoColumn);
  }

  /* event handler */
  handleClickAddButton(event) {
    const $targetColumn = event.target.closest('.todo-column');
    emit($targetColumn, '@clickAddButton');
  }

  handleClickCancelButton(event) {
    const $targetCard = event.target.closest('.todo-item');
    emit($targetCard, '@clickCancelButton');
  }

  handleClickDeleteCardButton(event) {
    const $targetCard = event.target.closest('.todo-item');
    emit($targetCard, '@clickDeleteCardButton');
  }

  handleInputTextarea(event) {
    const $targetTextarea = event.target;
    $targetTextarea.style.height = 'auto';
    $targetTextarea.style.height = `${$targetTextarea.scrollHeight}px`;
  }
}
