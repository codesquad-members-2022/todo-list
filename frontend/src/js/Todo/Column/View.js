import { columnBoxTemplate } from '../../utils/template.js';

export default class ColumnView {
  constructor() {}

  render(id, title, cardCount) {
    const columnBox = columnBoxTemplate({ id, title, cardCount });
    const todoContainer = document.querySelector('.todo_container');
    todoContainer.insertAdjacentHTML('beforeend', columnBox);
  }
}
