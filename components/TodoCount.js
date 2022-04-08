export default class TodoCount {
  constructor(status, count) {
    this.status = status;
    this.count = count;
  }

  addCount = () => {
    this.count++;
  };

  minusCount = () => {
    this.count--;
  };

  getCount = () => {
    return this.count;
  };

  render = () => {
    document.querySelector(`.${this.status} .column__count`).innerText = this.count;
  };
}
