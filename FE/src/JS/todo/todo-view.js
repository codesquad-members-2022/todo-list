import { $, $$ } from '../utility/util.js';
import { makeCategory, makeCard, addCardTemplate } from './todo-view-template.js';

export default class View {
  constructor() {
    this.columnList = $('.column-list');
  }

  renderTodoCategory(categoryName) {
    this.columnList.insertAdjacentHTML('beforeend', makeCategory(categoryName));
  }

  renderTodoCard(todoInfo, data) {
    const $container = this.findCurrentContainer(todoInfo.category);

    $container.insertAdjacentHTML('beforeend', makeCard(todoInfo, data));

    this.changeCategoryCount(todoInfo, $container);
  }

  findCurrentContainer = (categoryName) => $(`.container${categoryName}`);

  changeCategoryCount(todoInfo, $container) {
    $(`.itemCount${todoInfo.category}`).innerHTML =
      $container.childElementCount;
  }

  addCardTemplateEvent() {
    const addBtn = $$('.addBtn');
    addBtn.forEach(el => (el.addEventListener('click', this.showCardTemplate)));
  }

  showCardTemplate = ({target}) => {
    const columnName = target.closest('.column-item').id;
    const column = this.findCurrentContainer(columnName);
    column.insertAdjacentHTML('afterBegin', addCardTemplate())
  }
}
