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
