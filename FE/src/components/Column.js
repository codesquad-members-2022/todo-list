import { GET_CARD } from "../dummyData.js";
import { $ } from "../utils.js";
import { CardModificationForm } from "./CardModificationForm.js";
import TaskCard from "./TaskCard.js";

export default class Column {
  constructor(columnTitle) {
    this.columnTitle = columnTitle;
    this.taskCardComponents = GET_CARD.map((card) => new TaskCard(card));
    this.isOpenModificationForm = false;
  }
  template() {
    return `
      <div class="work">
        <div class="work__header">
          <div class="work__left">
            <h2 class="work__title">${this.columnTitle}</h2>
            <strong class="work__counter">${this.getCardLength()}</strong>
          </div>
          <div class="work__btn">
            <div 
              data-name="${this.columnTitle}" 
              class="work__btn--add ${this.columnTitle}-btn">
            </div>
            <div class="work__btn--remove"></div>
          </div>
        </div>
        <div class="work__body">
          <ul class="work__list ${this.columnTitle}-list">
            ${this.taskCardComponents.reduce(
              (prev, cur) =>
                prev + `<div class="${cur.id}">${cur.template()}</div>`,
              ""
            )}
          </ul>
        </div>
      </div>
    `;
  }
  getCardLength() {
    return this.taskCardComponents.length;
  }
  addEvent() {
    $("body")
      .querySelector(`.${this.columnTitle}-btn`)
      .addEventListener("click", (e) => this.handleCardAddition(e));
  }

  handleCardAddition(e) {
    if (!this.isOpenModificationForm) {
      this.showModificationForm(e);
      this.isOpenModificationForm = true;
      return;
    }
    this.hideModificationForm(e);
    this.isOpenModificationForm = false;
  }
  showModificationForm(e) {
    const columnName = e.target.dataset.name;
    const list = $(`.${columnName}-list`);
    const modificationForm = new CardModificationForm();
    list.insertAdjacentHTML("afterbegin", modificationForm.template());
  }
  hideModificationForm(e) {
    $(".task-deactivate").remove();
  }
}
