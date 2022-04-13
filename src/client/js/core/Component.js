class Component {
  constructor($target, $props = {}) {
    this.abortController = new AbortController();
    this.$target = $target;
    this.$props = $props;
    this.setup();
    this.render();
    this.setEvent();
  }

  setup() {}

  template() {
    return '';
  }

  mounted() {}

  render() {
    this.$target.innerHTML = this.template();
    this.mounted();
  }

  setState(newState) {
    this.$state = { ...this.$state, ...newState };
    this.render();
  }

  notify() {}

  setEvent() {}

  addEvent(eventType, selector, callback, useCapture = false) {
    const children = [ ...this.$target.querySelectorAll(selector) ];

    const isTarget = (target) => children.includes(target) || target.closest(selector);
    this.$target.addEventListener(eventType, event => {
      if (!isTarget(event.target)) return false;
      callback(event);
    }, { capture: useCapture, signal: this.abortController.signal });
  }

  removeEvent() {
    this.abortController.abort();
  }

  destroy() {
    this.removeEvent();
    this.$target.innerHTML = '';
  }
}

export default Component;
