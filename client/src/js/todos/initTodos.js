import { $, pipe } from '../util';

export class InitTodos {
  constructor(template) {
    this.target = $('main');
    this.template = template;
  }

  init = () => {
    pipe(this.getData, this.createColumns, this.renderColumns)('columns');
  };

  getData = async (keyword) => {
    const url = `http://localhost:3001/${keyword}`;
    const res = await fetch(url);
    const json = await res.json();
    return json;
  };

  createColumns = async (data) => {
    return data.map(this.template);
  };
  ygggggggg;
  renderColumns = async (data) => {
    this.target.insertAdjacentHTML('beforeend', data.join(' '));
  };

  getMaxId = async () => {
    const cardData = await this.getData('cards');

    if (cardData.length) {
      const maxId = cardData.reduce((prev, current) => {
        return Number(prev.id) > Number(current.id) ? prev : current;
      });

      return Number(maxId.id);
    } else {
      return 0;
    }
  };
}
