import Column from './Column.js';

export default class Columns {
  init() {
    const columnNames = ['TODO', 'ONGOING', 'DONE'];
    const columnComponents = columnNames.map(name => new Column(name));
  }

  template() {
    return `
      <div class="works">
        ${this.columnComponents.reduce((prev, x) => prev + x.template(), '')}
      </div>
    `;
  }
}