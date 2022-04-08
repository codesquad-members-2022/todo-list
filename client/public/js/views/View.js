export default class View {
  constructor(element) {
    if (!element) throw "no element";

    this.element = element;
    this.originalDisplay = this.element.style.dispaly || "";

    return this;
  }

  hide() {
    this.element.style.display = "none";
    return this;
  }

  show() {
    this.element.style.display = this.originalDisplay;
    return this;
  }
}
