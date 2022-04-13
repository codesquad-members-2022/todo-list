import ColumnModel from './Model.js';
import ColumnView from './View.js';

export default class Column {
  constructor({ id, title, cardCount = 0 }) {
    this.model = new ColumnModel(id, title, cardCount);
    this.view = new ColumnView();

    this.init({ id, title, cardCount });
  }

  init({ id, title, cardCount }) {
    this.view.render(id, title, cardCount);
  }
}
