import { GET_CARD } from "../dummyData.js";
import TaskCard from "./TaskCard.js";

export default class Column {
  constructor(columnTitle) {
    this.columnTitle = columnTitle;
    this.taskCardComponents = GET_CARD.map((card) => new TaskCard(card));
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
            <div class="work__btn--add"></div>
            <div class="work__btn--remove"></div>
          </div>
        </div>
        <div class="work__body">
          ${this.taskCardComponents.reduce(
            (prev, cur) => prev + cur.template(),
            ""
          )}
        </div>
      </div>
    `;
  }
  getCardLength() {
    return this.taskCardComponents.length;
  }
}
