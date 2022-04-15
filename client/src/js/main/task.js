import { $, $$ } from "../utils/utils.js";
import { iconDelete } from "../constants/imagePath.js";
import * as TodoListStore from "../store/todoListStore.js";
import { setMouseEvent } from "./taskDragHandler.js";
import { alertDeleteInit } from "./alertDelete.js";

export class Task {
  constructor(listTitle, taskData) {
    this.listTitle = listTitle;
    if (!taskData) return;
    const { title, comment, author, id } = taskData;
    [this.taskTitle, this.comment, this.author, this.taskId] = [title, comment, author, id];
  }

  init() {
    this.render();
    this.setTarget();
    this.setEvents();
  }

  render() {
    const lists = $$(".column__item");
    const position = this.taskId ? "beforeend" : "afterbegin";

    for (const list of lists) {
      if (list.dataset.title === this.listTitle) {
        list
          .querySelector(".column__task--list")
          .insertAdjacentHTML(position, this.taskId ? this.createHTML() : this.createRegistrationCardHTML());
      }
    }
  }

  createHTML(taskData) {
    if (taskData) {
      const { title, comment, author, id } = taskData;
      [this.taskTitle, this.comment, this.author, this.taskId] = [title, comment, author, id];
    }

    return this.taskId
      ? `<li class="column__task--item" data-title="${this.taskTitle}" data-id="${this.taskId}">
              <section>
                <div class="section__header">
                  <input readonly type="text" class="column__task--title" value="${this.taskTitle}"/>
                  <img src=${iconDelete} class="column__task--delete-button" />
                </div>
                <textarea readonly class="column__task--comment" spellcheck="false">${this.comment}</textarea>
                <span class="column__task--author">author by ${this.author}</span>
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
    this.setClickEvent();
    this.setDoubleClickEvent();
    this.setInputEvent();
    this.setKeyupEvent();
    this.setDeleteButtonMouseEvent();
    setMouseEvent(this.target);
  }

  setTarget() {
    const lists = $$(".column__item");
    for (const list of lists) {
      if (list.dataset.title === this.listTitle) {
        const tasks = list.querySelectorAll(".column__task--item");
        for (const task of tasks) {
          if (task.dataset.id == this.taskId) {
            this.target = task;
          }
        }
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
    if (!this.isTaskButton(target)) return;

    if (this.isCancelButton(target))
      if (this.isRegistrationCard()) return this.removeRegistrationCard();
      else if (this.isEditCard()) return this.restoreOriginCard();

    if (this.isInactivation()) return;

    this.taskTitle = this.target.querySelector("input").value;
    this.comment = this.target.querySelector("textarea").value;
    this.taskData = {
      id: this.taskId || null,
      title: this.taskTitle,
      comment: this.comment,
      author: this.author ? this.author : "web",
    };

    return TodoListStore.update("newTask", this.listTitle, this.taskData);
  }

  isTaskButton(target) {
    return target.closest(".column__task--button");
  }

  isCancelButton(target) {
    return target.classList.contains("column__task--cancel-button");
  }

  isRegistrationCard() {
    return this.target.classList.contains("registration-card");
  }

  isEditCard() {
    return this.target.classList.contains("edit-card");
  }

  isInactivation() {
    return this.target.classList.contains("inactivation");
  }

  removeRegistrationCard() {
    this.target.remove();
  }

  restoreOriginCard() {
    this.target.classList.remove("edit-card");
    this.target.querySelector(".column__task--button").remove();
    this.target.querySelector("input").readOnly = true;
    this.target.querySelector("textarea").readOnly = true;
    this.target
      .querySelector("section")
      .insertAdjacentHTML("beforeend", `<span class="column__task--author">author by ${this.author}</span>`);
  }

  setDoubleClickEvent() {
    this.target.addEventListener("dblclick", this.handleDoubleClickEvent.bind(this));
  }

  handleDoubleClickEvent() {
    this.target.classList.add("edit-card", "inactivation");
    this.target.querySelector("span").remove();
    this.target.querySelector("input").readOnly = false;
    this.target.querySelector("textarea").readOnly = false;
    this.target.querySelector("section").insertAdjacentHTML(
      "beforeend",
      `<div class="column__task--button">
        <button class="column__task--cancel-button">취소</button>
        <button class="column__task--accent-button">수정</button>
      </div>`
    );
  }

  setKeyupEvent() {
    this.target.addEventListener("keyup", this.handleKeyupEvent.bind(this));
  }

  handleKeyupEvent() {
    const taskTitle = this.target.querySelector("input").value;
    const comment = this.target.querySelector("textarea").value;
    const maxCommentNum = 500;
    if (comment.length > maxCommentNum) comment.disabled = true;
    if (taskTitle || comment) {
      this.target.classList.remove("inactivation");
    }
    if (
      (!taskTitle && !comment) ||
      (this.isEditCard() && taskTitle === this.taskTitle && comment === this.comment)
    )
      this.target.classList.add("inactivation");
  }

  setDeleteButtonMouseEvent() {
    this.deleteButton = this.target.querySelector(".column__task--delete-button");
    this.deleteButton.addEventListener("mouseover", this.handleMouseToggle.bind(this));
    this.deleteButton.addEventListener("mouseout", this.handleMouseToggle.bind(this));
    this.deleteButton.addEventListener("click", this.handleDeleteButtonClick.bind(this));
  }

  handleMouseToggle() {
    if (this.isAlertPopped()) {
      return;
    }

    const classBackground = "delete-background";
    const classBorder = "delete-border";
    const classButtonHover = "delete-hover";

    const title = this.target.querySelector(".column__task--title");
    const comment = this.target.querySelector(".column__task--comment");
    const deleteButton = this.target.querySelector(".column__task--delete-button");

    this.target.classList.toggle(classBackground);
    this.target.classList.toggle(classBorder);
    title.classList.toggle(classBackground);
    comment.classList.toggle(classBackground);
    deleteButton.classList.toggle(classButtonHover);
  }

  isAlertPopped() {
    const alert = $(".dimmed");
    return alert !== null;
  }

  handleDeleteButtonClick() {
    alertDeleteInit({ listTitle: this.listTitle, taskId: this.taskId }, this.cancelAlert.bind(this));
  }

  cancelAlert() {
    this.handleMouseToggle();
  }
}
