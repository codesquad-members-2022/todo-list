import { $, createElement } from '../utils/utils.js';
import { TodoList } from './TodoList.js';
import { TodoItem } from './TodoItem.js';

export class TodoColumns{
  constructor($element) {
    this.$element = $element;
    this.columns = [];
  }

  init(columnsData) {
    columnsData.forEach((columnData) => {
      const todoList = new TodoList(null);
      todoList.init(columnData);
      this.columns.push(todoList.$element);
    })

    this.$element.append(...this.columns);
  }
}