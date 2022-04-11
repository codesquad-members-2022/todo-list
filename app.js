import Component from "./core/Component.js";

import { Header } from "./components/Header.js";
import { TodoList } from "./components/TodoList.js";

class App extends Component {
  setup() {
    this.state = {
      lists: [
        {
          title: "오늘 할 일",
          todos: [
            {
              title: "코드스쿼드",
              content: "수업듣기",
              caption: "",
              selected: false
            }
          ],
          editting: false
        }
      ]
    };
  }

  toggleSelected(listIdx, cardIdx) {
    const { lists } = this.state;
    const newList = [...lists];
    const boolean = newList[listIdx].todos[cardIdx].selected;
    newList[listIdx].todos[cardIdx].selected = !boolean;
    this.setState({ lists: newList });
  }

  template() {
    const { lists } = this.state;
    return `<header class="todo-header" >
          </header>
          ${lists
      .map(
        (list, idx) =>
          `<section class="todo-column" data-idx="${idx}" ></section>`
      )
      .join("")}`;
  }

  mount() {
    const { lists } = this.state;
    new Header(this.select(".todo-header"));
    lists.forEach(
      (list, idx) =>
        new TodoList(this.select(`section[data-idx="${idx}"`), {
          list,
          idx
        })
    );
  }
}

new App(document.body);
