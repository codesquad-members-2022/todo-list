import Column from "./Column.js";

export default class Columns {
  constructor(target) {
    this.target = target;
    const columnTitles = ["해야할 일", "하고 있는 일", "완료한 일"];
    this.columnComponents = columnTitles.map((name) => new Column(name));
  }

  template() {
    return `
      ${this.columnComponents.reduce(
        (prev, column) => prev + column.template(),
        ""
      )}
    `;
  }
  render() {
    this.target.innerHTML = this.template();
  }
}
