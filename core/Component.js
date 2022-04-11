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

  setup() {
  }

  render() {
    this.#target.innerHTML = this.template();
    this.mount();
  }

  mount() {
  }

  template() {
    return ``;
  }

  addEvent(eventType, selector, callback) {
    const children = [...this.#target.querySelectorAll(selector)];
    const isTarget = (target) =>
      children.includes(target) || target.closest(selector);
    this.#target.addEventListener(eventType, (e) => {
      if (!isTarget(e.target)) return false;
      callback(e);
    });
  }

  setEvent() {
  }

  select(selector) {
    return this.#target.querySelector(selector);
  }

  selectAll(selector) {
    return this.#target.querySelectorAll(selector);
  }
}
