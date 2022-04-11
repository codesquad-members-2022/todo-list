import TaskCard from './TaskCard.js';

export default class Column {
  init() {
    const taskCards = [1, 2, 3];
    const taskCardComponents = taskCards.map(e => new TaskCard());
  }

  template(state) {
    return `
      <div class="work">
        <div class="work__header">
          <div class="work__left">
            <h2 class="work__title">${state.title}</h2>
            <strong class="work__counter">${this.counter()}</strong>
          </div>
          <div class="work__btn">
            <div class="work__btn--add"></div>
            <div class="work__btn--remove"></div>
          </div>
        </div>
        <div class="work__body">
          ${taskCardComponents.reduce((acc, cur) => {
            acc + cur.template(state); // state.?
          }, '')}
        </div>
      </div>
    `
  }

  counter() {
    // 프론트엔드단에서 카운터 길이를 구해서 리턴
    return this.counter;
  }
}
