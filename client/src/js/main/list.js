import { $, $$ } from "../utils/utils.js";
import { iconAdd, iconDelete } from "../constants/imagePath.js";
import { Task } from "./task.js";
import * as TodoListStore from "../store/todoListStore.js";

export class List {
  constructor(parent, listData) {
    this.parent = parent;
    [this.listTitle, this.taskData] = listData;
    this.init();
  }

  init() {
    this.render(this.parent);
    this.createTask(this.taskData);
    this.setEvents();
    TodoListStore.subscribe("registration", this.notifyRegistration.bind(this));
<<<<<<< HEAD
    TodoListStore.subscribe("newTask");
=======
    TodoListStore.subscribe("newTask", this.notifyListUpdate.bind(this));
  }

  async notifyListUpdate(id, listTitle, newTask) {
    if (this.listTitle !== listTitle) {
      return;
    }

    const allListData = await TodoListStore.getTodoListData();
    const currentListData = allListData.filter((listData) => listData.title === listTitle)[0];
    this.taskData = currentListData.task;
    const column_list = this.target.querySelector(".column__task--list");
    const column_task_count = this.target.querySelector(".column__task--count");
    column_list.innerHTML = "";
    column_task_count.innerText = this.taskData.length;
    this.createTask(this.taskData);
>>>>>>> a40f77b (feat: 카드 삭제 시 리스트 리렌더링 기능 추가)
  }

  notifyRegistration(activation, title) {
    if (title !== this.listTitle) return;
    if (!activation && !this.target.querySelector(".registration-card")) {
      const originRegistrationCard = $(".registration-card");
      originRegistrationCard && originRegistrationCard.remove();
      return TodoListStore.update("registration", this.listTitle);
    }
    activation ? this.addRegistrationCard() : this.removeRegistrationCard();
  }

  createTask(tasksData) {
    for (const task of tasksData) {
      new Task(this.listTitle, task);
    }
  }

  render(parent) {
    const position = "beforeend";
    parent.insertAdjacentHTML(position, this.createHTML(this.listTitle));
  }

  createHTML(title) {
    return `<li class="column__item" data-title="${title}">
          <div class="column__item--title">
            <div class="column__item--title-text">
              <h2 class="column__title">${title}</h2>
              <div class="column__task--count">${this.taskData.length}</div>
            </div>
            <div class="column__item--title-menu">
              <img src=${iconAdd} class="column__task--add-button" />
              <img src=${iconDelete} class="column__list--delete-button" />
            </div>
          </div>
          <ul class="column__task--list"></ul>
        </li>`;
  }

  setEvents() {
    this.setTarget();
    this.setClickEvent();
  }

  setTarget() {
    const lists = $$(".column__item");
    for (const list of lists) {
      if (list.dataset.title === this.listTitle) {
        this.target = list;
      }
    }
  }

  setClickEvent() {
    this.target.addEventListener("click", ({ target }) => this.handleClickEvent(target));
  }

  handleClickEvent(target) {
    const isAddButton = target.classList.contains("column__task--add-button");
    if (!isAddButton) return;
    TodoListStore.update("registration", this.listTitle);
  }

  addRegistrationCard() {
    new Task(this.listTitle);
  }

  removeRegistrationCard() {
    const firstTask = this.target.querySelector(".column__task--item");
    firstTask.remove();
  }
}
