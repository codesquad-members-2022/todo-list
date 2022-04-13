import Column from "./Column.js";

export default class Columns {
  constructor(target) {
    this.target = target;
    const columnTitles = ["TODO", "ONGOING", "DONE"];
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

    this.columnComponents.forEach((column) => {
      column.addEvent();
    });
  }
}
