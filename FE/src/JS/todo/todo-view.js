import { $ } from '../utility/util.js';
import { makeCategory, makeCard } from './todo-view-template.js';

export default class View {
  constructor() {
    this.columnList = $('.column-list');
  }

  renderTodoCategory(categoryName) {
    this.columnList.insertAdjacentHTML('beforeend', makeCategory(categoryName));
  }

  renderTodoCard(todoInfo, data) {
    const $container = this.findCategoryName(todoInfo.category);

    $container.insertAdjacentHTML('beforeend', makeCard(todoInfo, data));

    this.changeCategoryCount(todoInfo, $container);
  }

  findCategoryName = (categoryName) => $(`.container${categoryName}`);

  changeCategoryCount(todoInfo, $container) {
    $(`.itemCount${todoInfo.category}`).innerHTML =
      $container.childElementCount;
  }
}
