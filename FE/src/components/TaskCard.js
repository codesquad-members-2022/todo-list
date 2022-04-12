import { store } from "../store.js";
import { $ } from "../utils.js";

export default class TaskCard {
  constructor(card) {
    this.id = card.id;
    store.initState(this.id);
    store.setState(this.id, card);
  }
  template() {
    // types = ["idle", "delete", "drag", "place"];
    const type = "idle";
    const card = store.state[this.id];
    return `
        <li class="work__item">
          <div class="task-${type}">
            <div class="task-${type}__contents">
              <h3 class="task-${type}__title">${card.title}</h3>
              <p class="task-${type}__desc">${card.contents}</p>
              <strong class="task-${type}__author">author by ${card.userId}</strong>
            </div>
            <div class="task-${type}__btn">
              <div class="task-${type}__btn--remove"></div>
            </div>
          </div>
        </li>
    `;
  }
  render() {
    $(`.${this.id}`).innerHTML = this.template();
    store.subscribe(this.id, this.render().bind(this));
  }
}
