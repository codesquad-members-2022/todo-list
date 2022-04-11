export default class TaskCard {
  constructor() {}
  init() {}
  template() {
    // states = ["idle", "delete", "drag", "place"];
    const state = "idle";
    return `
      <ul class="work__list">
        <li class="work__item">
          <div class="task-${state}">
            <div class="task-${state}__contents">
              <h3 class="task-${state}__title">GitHub 공부하기</h3>
              <p class="task-${state}__desc">add, commit, push</p>
              <strong class="task-${state}__author">author by web</strong>
            </div>
            <div class="task-${state}__btn">
              <div class="task-${state}__btn--remove"></div>
            </div>
          </div>
        </li>
      </ul>
    `;
  }
}
