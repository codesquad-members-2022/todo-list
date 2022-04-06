import Component from "./Component.js";
import { TodoCard } from "./TodoCard.js";

export class TodoList extends Component {
  setup() {
    this.state = {
      title: "오늘할 일",
      selected: -1,
      todos: [{ title: "report", contents: "운영체제", caption: "과제" }],
    };
  }
  template() {
    const { todos, title, selected } = this.state;
    console.log(todos);
    return `
<div class="todo-title">
            <span class="todo-name">${title}
              <div class="count"><span class="number">${
                todos.length
              }</span></div>
            </span>
      <div class="todo-title-btn">
                <span class="add">
                    <svg width="14" height="14" viewBox="0 0 14 14" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path
                          d="M0.105713 7.53033L0.105713 6.46967H6.46967V0.105713H7.53033V6.46967H13.8943V7.53033H7.53033V13.8943H6.46967V7.53033H0.105713Z"
                          fill="#BDBDBD" />
                    </svg>
                </span>
        <span class="delete">
                    <svg width="12" height="12" viewBox="0 0 12 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path
                          d="M1.5 11.25L0.75 10.5L5.25 6L0.75 1.5L1.5 0.75L6 5.25L10.5 0.75L11.25 1.5L6.75 6L11.25 10.5L10.5 11.25L6 6.75L1.5 11.25Z"
                          fill="#BDBDBD" />
                    </svg>
                </span>
      </div>
    </div>
    ${todos
      .map((todo, idx) => `<div class="todo-card" data-idx=${idx}></div>>`)
      .join("")}`;
  }

  mount() {
    const { todos } = this.state;
    console.log(this.select(`[data-idx="0"]`));
    for (let i = 0; i < todos.length; i++) {
      new TodoCard(this.select(`div[data-idx="${i}"]`));
    }
  }
}
