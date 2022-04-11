import TodoColumn from './TodoColumn';

export default class TodoColumns {
  constructor($columns, columns = []) {
    this.$columns = $columns;
    this.columns = columns;
  }

  init(columnsData) {
    columnsData.forEach(columnData => {
      const todoColumn = new TodoColumn();
      todoColumn.init(columnData);
      this.columns.push(todoColumn.$todoColumn);
    });

    this.render();
  }

  render() {
    this.$columns.append(...this.columns);
  }
}
