



import "./styles/scss/index.css";
import { TodoCard } from "./components/TodoCard";
import { Store } from "./core/Store";
import { TodoList } from "./components/TodoList";
import { Header } from "./components/Header";
import View from "./core/View";
import { StateObj } from "./utils";




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
        new TodoList(this.store,this.select(`section[data-idx="${idx}"`)!, list,this)
    );
  }
}

const store = new Store({
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
})
new App( store, document.body );
