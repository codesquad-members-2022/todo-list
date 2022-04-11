import { $, $$ } from "../utils/utils.js";
import { iconAdd, iconDelete } from "../constants/imagePath.js";
import { Task } from "./task.js";
import * as TodoListStore from "../store/todoListStore.js";

export class List {
  constructor(parent, listData) {
    this.task = [];
    this.parent = parent;
    [[this.title, this.tasksData]] = Object.entries(listData);
    this.init();
  }

  init() {
    this.render(this.parent);
    this.createTask(this.tasksData);
    this.setEvents();
    TodoListStore.subscribe("registration", this.notifyRegistration.bind(this));
    TodoListStore.subscribe("newTask", this.notifyNewTask.bind(this));
  }

  notifyRegistration(activation, title) {
    if (title !== this.title) return;
    if (!activation && !this.target.querySelector(".registration-card")) {
      const originRegistrationCard = $(".registration-card");
      originRegistrationCard && originRegistrationCard.remove();
      return TodoListStore.update("registration", this.title);
    }
    if (!activation) this.task.shift();
    activation ? this.addRegistrationCard() : this.removeRegistrationCard();
  }

  notifyNewTask(newTaskCount, title, newTask) {
    if (title !== this.title) return;
    this.task.push(new Task(this.title, newTask));
    TodoListStore.update("registration", this.title);
    this.target.querySelector(".column__task--count").innerHTML = this.task.length;
  }

  createTask(tasksData) {
    for (const task of tasksData) {
      this.task.push(new Task(this.title, task));
    }
  }

  render(parent) {
    const position = "beforeend";
    parent.insertAdjacentHTML(position, this.createHTML(this.title));
  }

  createHTML(title) {
    return `<li class="column__item" data-title="${title}">
          <div class="column__item--title">
            <div class="column__item--title-text">
              <h2 class="column__title">${title}</h2>
              <div class="column__task--count">${this.tasksData.length}</div>
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
      if (list.dataset.title === this.title) {
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
    TodoListStore.update("registration", this.title);
  }

  addRegistrationCard() {
    this.task.unshift(new Task(this.title));
  }

  removeRegistrationCard() {
    const firstTask = this.target.querySelector(".column__task--item");
    firstTask.remove();
  }
}
