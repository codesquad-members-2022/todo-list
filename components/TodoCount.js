export default class TodoCount {
  constructor(count) {
    this.count = count;
    this.render();
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

  render = () => {};
}
