import TodoColumn from './TodoColumn';

export default class TodoColumns {
  constructor($todoColumns) {
    this.$todoColumns = $todoColumns;
    this.todoColumns = [];
  }

  init(columnsData) {
    columnsData.forEach(columnData => {
      const todoColumn = new TodoColumn(columnData);
      this.todoColumns.push(todoColumn);
    });

    this.render();
  }

  render() {
    const $$todoColumn = this.todoColumns.map(column => column.$todoList);
    this.$todoColumns.append(...$$todoColumn);
  }
}
