export default class TaskCard {
  constructor(state) {
    this.state = state;
  }
  template() {
    // types = ["idle", "delete", "drag", "place"];
    const type = "idle";
    return `
      <ul class="work__list">
        <li class="work__item">
          <div class="task-${type}">
            <div class="task-${type}__contents">
              <h3 class="task-${type}__title">${this.state.title}</h3>
              <p class="task-${type}__desc">${this.state.contents}</p>
              <strong class="task-${type}__author">author by ${this.state.userId}</strong>
            </div>
            <div class="task-${type}__btn">
              <div class="task-${type}__btn--remove"></div>
            </div>
          </div>
        </li>
      </ul>
    `;
  }
}
