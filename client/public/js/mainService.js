export default class MainService {
  constructor({ targetEl }) {
    this.targetEl = targetEl;
    this.dataList = "";
  }

  init(dataList) {
    this.dataList = dataList.flat(2).join("");
    return this.render(this.dataList);
  }

  render(data) {
    return `<span>${data}</span>`;
  }
}

const box = {
  weight: 2,
  getWeight() {
    return this.weight;
  },
};

const { getWeight } = box;

console.log(box.getWeight()); // prints '2'

const bigBox = { weight: 10 };
console.log(bigBox::getWeight()); // prints '10'
