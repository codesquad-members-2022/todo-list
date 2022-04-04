export default class Component {
  constructor() {
    this.state = state;
    this.el = el;
    this.setEvent();
    this.render();
  }

  setState(newState) {
    this.state = { ...this.state, ...newState };
    this.render();
  }

  render() {
    this.el.innerHTML = this.template();
  }

  template() {
    return ``;
  }

  setEvent() {}

  select(selector) {
    return this.el.querySelector(selector);
  }

  selectAll(selector) {
    return this.el.querySelectAll(selector);
  }
}
