export default class Component {
  state;
  $props;
  #target;
  constructor(target, props = {}) {
    this.#target = target;
    this.$props = props;
    this.setup();
    this.setEvent();
    this.render();
  }

  setState(newState) {
    this.state = { ...this.state, ...newState };
    this.render();
  }

  render() {
    this.#el.innerHTML = this.template();
    this.mount();
  }
  mount() {}
  template() {
    return ``;
  }

  setEvent() {}

  select(selector) {
    return this.#el.querySelector(selector);
  }

  selectAll(selector) {
    return this.#el.querySelectorAll(selector);
  }
}
