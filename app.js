import Component from "./core/Component.js";
import { MainSection } from "./core/MainSection.js";
import { Header } from "./core/Header.js";

class App extends Component {
  template() {
    return `
          <header class="todo-header">
          </header>
          <main>        
          </main>
        `;
  }
  mount() {
    new Header(this.select(".todo-header"));
    new MainSection(this.select("main"));
  }
}
new App(document.body);
