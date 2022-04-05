import { TodoList } from "./TodoList";
import { MainSection } from "./core/TodoList";

class App {
  template() {
    const { lists } = this.state;
    return `
          <header class="todo-header">
          </header>
          <main>        
          </main>
        `;
  }
  mount() {
    new Header();
    new MainSection();
  }
}
new App(document.body);

앱 - TodoList - TodoCard -

변화가 일어나는 것(이벤트):
MainSection
this.state = {
  list: [
    TodoList:{
      selected: -1,
      title: "해야할 일",
      todos: [
        TodoCard:{
          title: "",
          contents: "",
          caption: "",
        },
      ],
    },
    {
      selected: -1,
      title: "하고 있는 일",
      todos: [
        {
          title: "",
          contents: "",
          caption: "",
        },
      ],
    },
    {},
  ],
};