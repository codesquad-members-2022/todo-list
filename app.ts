
import "./styles/scss/index.scss";
import {loadStore, Store} from "./core/Store";
import {TodoList} from "./components/TodoList";
import {Header} from "./components/Header";
import View from "./core/View";
import {StateObj} from "./types";



class App extends View {


    template() {
        const {lists} = this.state;
        return `<header class="todo-header" >
          </header>
          ${lists
            .map(
                (list: StateObj, idx: number) =>
                    `<section class="todo-column" data-idx="${idx}" ></section>`
            )
            .join("")}`;
    }

    mount() {
        const {lists} = this.state;
        new Header(this.store, this.select(".todo-header")!);
        lists.forEach(
            (list: StateObj, idx: number) =>
                new TodoList(this.store, this.select(`.todo-column[data-idx="${idx}"`), {list, listIdx: idx})
        );
    }
}

loadStore().then(store => {
    new App(store, document.body);

})

