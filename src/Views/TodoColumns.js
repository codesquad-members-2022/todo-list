import { emit, addClass, removeClass, eventDelegate } from '../utils/utils';
import TodoColumn from './TodoColumn';

export default class TodoColumns {
  constructor($todoColumns) {
    this.$todoColumns = $todoColumns;
    this.todoColumns = [];
    this.addEventHandlers();
  }

  init(columnsData) {
    columnsData.forEach(columnData => {
      const todoColumn = new TodoColumn(columnData);
      this.todoColumns.push(todoColumn);
    });

    this.render();
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

    eventDelegate({
      target: this.$todoColumns,
      eventName: 'mouseover',
      selector: '.todo-item .delete-button',
      handler: event => {
        this.handleMouseoverDeleteCardButton(event);
      },
    });

    eventDelegate({
      target: this.$todoColumns,
      eventName: 'mouseout',
      selector: '.todo-item .delete-button',
      handler: event => {
        this.handleMouseoutDeleteCardButton(event);
      },
    });
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

  handleMouseoverDeleteCardButton(event) {
    const $targetCard = event.target.closest('.todo-item');
    addClass('delete-item', $targetCard);
  }

  handleMouseoutDeleteCardButton(event) {
    const $modal = event.relatedTarget.closest('.modal');
    if ($modal) return;
    const $targetCard = event.target.closest('.todo-item');
    removeClass('delete-item', $targetCard);
  }
}
