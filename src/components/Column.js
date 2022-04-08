import Component from "../core/Component.js";

export default class Column extends Component {
  setup() {
    this.state = {
      title: "해야할 일",
      cardCount: 0,
    };
  }
  template() {
    const { title, cardCount } = this.state;
    return `
      <div class="column-header">
        <div class="flex">
          <h1 class="column-title">${title}</h1>
          <div class="card-count">${cardCount}</div>
        </div>
        <div class="flex">
          <div class="column-plus-button"></div>
          <div class="column-delete-button"></div>
        </div>
      </div>
      <div class="card-wrapper"></div>
    `;
  }
}
