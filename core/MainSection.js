import Component from "./Component";

export class MainSection extends Component {
  template() {
    const { lists } = this.state;
    return `
    ${lists.map((list, idx) => `<section data-idx=${idx}></section>`)}
    `;
  }
  mount() {
    const { list } = this.state;
    for (let i = 0; i < lists.length; i++) {
      new TodoList(this.select(`todo-column[data-idx='${i}']`));
    }
  }
  setEvent() {}
}
