
import "./styles/scss/index.css";
import { foo, Store } from "./core/Store";
import { TodoList } from "./components/TodoList";
import { Header } from "./components/Header";
import View from "./core/View";
import { StateObj } from "./types";




class App extends View {



  template() {
    const { lists } = this.state;
    console.log(lists);
    return `<header class="todo-header" >
          </header>
          ${lists
      .map(
        (list:StateObj, idx:number) =>
         `<section class="todo-column" data-idx="${idx}" ></section>`
      )
      .join("")}`;
  }

  mount() {
    const { lists } = this.state;
    new Header(this.store,this.select(".todo-header")!);
    lists.forEach(
      (list:StateObj, idx:number) =>
        new TodoList(this.store,this.select(`section[data-idx="${idx}"`)!, list)
    );
  }
}

foo().then(store=>{
  new App( store, document.body );
})

