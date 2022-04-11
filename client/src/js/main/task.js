import { $$ } from "../utils/utils.js";
import { iconDelete } from "../constants/imagePath.js";
import { setMouseEvent } from "./taskDragHandler.js";
import * as TodoListStore from "../store/todoListStore.js";

export class Task {
  constructor(title, taskData) {
    this.title = title;
    this.taskData = taskData;
    this.init();
    this.setEvents();
  }

  init() {
    this.render();
  }

  render() {
    const lists = $$(".column__item");
    const position = this.taskData ? "beforeend" : "afterbegin";

    for (const list of lists) {
      if (list.dataset.title === this.title) {
        list
          .querySelector(".column__task--list")
          .insertAdjacentHTML(
            position,
            this.taskData ? this.createHTML() : this.createRegistrationCardHTML()
          );
      }
    }
  }

  createHTML(taskData) {
    taskData = taskData || this.taskData;
    const { title, comment, author } = taskData;
    this.taskTitle = title;
    return taskData
      ? `<li class="column__task--item" data-title="${title}">
              <section>
                <div class="section__header">
                  <input readonly type="text" class="column__task--title" value="${title}" />
                  <img src=${iconDelete} class="column__task--delete-button" />
                </div>
                <textarea readonly class="column__task--comment" spellcheck="false">${comment}</textarea>
                <span class="column__task--author">author by ${author}</span>
              </section>
              
            </li>`
      : "";
  }

  createRegistrationCardHTML() {
    return `
      <li class="column__task--item registration-card inactivation">
        <section>
          <div class="section__header">
            <input type="text" class="column__task--title" placeholder="제목을 입력하세요" />
            <img src=${iconDelete} class="column__task--delete-button" />
          </div>
          <textarea class="column__task--comment" spellcheck="false" placeholder="내용을 입력하세요"></textarea>
          <div class="column__task--button">
            <button class="column__task--cancel-button">취소</button>
            <button class="column__task--accent-button">등록</button>
          </div>
        </section>
      </li>
    `;
  }

  setEvents() {
    this.setTarget();
    this.setClickEvent();
    this.setInputEvent();
    this.setKeyupEvent();
    setMouseEvent(this.target);
  }

  setTarget() {
    const tasks = $$(".column__task--item");
    for (const task of tasks) {
      if (task.dataset.title === this.taskTitle) {
        this.target = task;
      }
    }
  }

  setInputEvent() {
    const columnTaskComment = this.target.querySelector(".column__task--comment");
    columnTaskComment.addEventListener("input", ({ target }) => this.autosizeTextArea(target));
  }

  autosizeTextArea(target) {
    target.style.height = "1px";
    target.style.height = target.scrollHeight + "px";
  }

  setClickEvent() {
    this.target.addEventListener("click", ({ target }) => this.handleClickEvent(target));
  }

  handleClickEvent(target) {
    const isTaskButton = target.closest(".column__task--button");
    if (!isTaskButton) return;

    const isCancelButton = target.classList.contains("column__task--cancel-button");
    if (isCancelButton) return this.removeRegistrationCard();

    const isInactivation = this.target.classList.contains("inactivation");
    if (isInactivation) return;
    const title = this.target.querySelector("input").value;
    const comment = this.target.querySelector("textarea").value;
    const newTask = { title, comment, author: "web" };
    TodoListStore.update("newTask", this.title, newTask);
  }

  removeRegistrationCard() {
    this.target.remove();
  }

  setKeyupEvent() {
    this.target.addEventListener("keyup", this.handleKeyupEvent.bind(this));
  }

  handleKeyupEvent() {
    const title = this.target.querySelector("input");
    const comment = this.target.querySelector("textarea");
    const maxCommentNum = 500;
    if (comment.value.length > maxCommentNum) comment.disabled = true;
    if (title.value || comment.value) this.target.classList.remove("inactivation");
    else this.target.classList.add("inactivation");
  }
}
