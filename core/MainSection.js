import Component from "./Component.js";
import { TodoList } from "./TodoList.js";

export class MainSection extends Component {
  setup() {
    this.state = { lists: [{ title: "오늘 할 일" }] };
  }
  template() {
    const { lists } = this.state;
    return `
    ${lists.map((list, idx) => `<section data-idx=${idx}></section>`)}
    `;
  }
  mount() {
    const { lists } = this.state;
    console.log(this.select("section[data-idx]"));
    for (let i = 0; i < lists.length; i++) {
      new TodoList(this.select(`section[data-idx="${i}"]`));
    }
  }
  setEvent() {}
}
