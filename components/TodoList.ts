

import View from "../core/View";
import { Action,  StateObj } from "../types";
import { TodoCard } from "./TodoCard";
import {AddDto} from "../core/DTOs/add.dto";
import {SelectDto} from "../core/DTOs/select.dto";
import {TodoForm} from "./TodoForm";


export class TodoList extends View {
  template() {
    const {
    title, todos, selectedIndex
    } = this.$props;
    return `
          <div class="todo-title">
            <span class="todo-name">${title}
              <span class="count"><span class="number">${
      todos.length
    }</span></span>
            </span>
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
      
    ${todos
      .map(
        (todo:StateObj, idx:number) =>
       `<div class="todo-card" data-idx="${idx}"></div>`
    )
      .join("")}`;
  }

  mount() {
    const { todos,selectedIndex } = this.$props;
    todos.forEach(
      (todo:StateObj, idx:number) => {
        const target = this.select(`.todo-card[data-idx="${idx}"]`)
        const listIdx = this.select().dataset.idx;
        selectedIndex===idx?
            new TodoForm(this.store, target, {todo,listIdx,idx}):
        new TodoCard(this.store, target, {
          todo,
          idx,
          listIdx
        })
      }
    );
  }
  setEvent(){
    const{todos, listIdx}= this.$props;
    this.addEvent('click', '.add' , e=>{
      this.store.commit(Action.ADD, new AddDto(listIdx, "", "")); //target idx
      this.store.commit(Action.SELECT, new SelectDto(true, 0, listIdx));
    })

  }
}

