export default class Component {
  constructor($target, $props) {
    console.log($target);
    this.$target = $target;
    this.$props = $props;
    this.setup();
    this.setEvent();
    this.render();
  }
  setup() {}

  setEvent() {}

  setState(newState) {
    this.state = { ...this.state, ...newState };
    this.render();
  }

  mounted() {}

  render() {
    this.$target.innerHTML = this.template();
    this.mounted();
  }

  template() {
    return "";
  }

  addEvent(type, selector, callback) {
    this.$target.addEventListener(type, (e) => {
      if (e.target.closest(selector)) {
        callback(e);
      }
    });
  }
}
